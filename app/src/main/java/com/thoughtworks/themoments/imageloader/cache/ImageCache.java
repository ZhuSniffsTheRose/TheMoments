package com.thoughtworks.themoments.imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.LruCache;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.os.Environment.isExternalStorageRemovable;

/**
 * Created by Zhu on 2019-11-26
 */
public class ImageCache {
    //内存缓存
    private LruCache<String, Bitmap> memoryCache;

    //硬盘缓存
    private DiskLruCache diskLruCache;

    //硬盘缓存锁，应为硬盘缓存涉及到文件的操作，用来控制线程安全
    private final Object diskCacheLock = new Object();
    //硬盘缓存是否正在初始化，未初始化完成，其它缓存Task必须等待
    private boolean diskCacheStarting = true;
    //硬盘缓存大小
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10;
    //硬盘缓存目录名称
    private static final String DISK_CACHE_SUBDIR = "thumbnails";


    public ImageCache(Context context) {
        //初始化内存缓存，指定内存缓存大小，并实现sizeof方法计算每个缓存实体的大小
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };

        //异步任务初始化硬盘缓存
        File cacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR);
        new InitDiskCacheTask().execute(cacheDir);
    }

    /**
     * 初始化硬盘缓存异步任务
     */
    class InitDiskCacheTask extends AsyncTask<File, Void, Void> {
        @Override
        protected Void doInBackground(File... params) {
            synchronized (diskCacheLock) {
                try {
                    //调用open()方法，指定缓存目录，初始化硬盘缓存。完毕后释放锁让其它线程执行
                    File cacheDir = params[0];
                    diskLruCache = DiskLruCache.open(cacheDir, 1, 1, DISK_CACHE_SIZE);
                    diskCacheStarting = false;
                    diskCacheLock.notifyAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    /**
     * 获取硬盘缓存目录，优先使用SD卡或者内置外存
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
                        context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }


    /**
     * 从内存缓存中获取指定url的图片
     *
     * @param url url
     * @return 位图
     */
    public Bitmap getBitmapFromMemCache(String url) {
        return memoryCache.get(url);
    }


    /**
     * 从磁盘缓存中获取url的位图
     *
     * @param url url
     * @return 位图
     */
    public Bitmap getBitmapFromDiskCache(String url) {
        synchronized (diskCacheLock) {
            //如果磁盘缓存正在初始化，则等待初始化完成
            while (diskCacheStarting) {
                try {
                    diskCacheLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            Bitmap bitmap = null;
            if (diskLruCache != null) {
                InputStream inputStream = null;
                //将图片的url生成md5哈希值作为硬盘缓存的key
                final String key = hashKeyForDisk(url);
                try {
                    //通过Snapshot获取输出流，读取key对应的缓存文件
                    DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                    if (snapshot != null) {
                        inputStream = snapshot.getInputStream(0);
                        if (inputStream != null) {
                            FileDescriptor fileDescriptor = ((FileInputStream) inputStream).getFD();
                            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }
    }

    /**
     * 生成图片url对应的mds值作为key
     */
    public static String hashKeyForDisk(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }


    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 向内存缓存和硬盘缓存缓存位图
     *
     * @param url    url
     * @param bitmap 位图
     */
    public void addBitmapToCache(String url, Bitmap bitmap) {
        //保存到内存缓存
        if (getBitmapFromMemCache(url) == null) {
            memoryCache.put(url, bitmap);
        }

        //保存到硬盘缓存
        synchronized (diskCacheLock) {
            if (diskLruCache != null) {
                final String key = hashKeyForDisk(url);
                OutputStream outputStream = null;
                try {
                    DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
                    //不存在则获取Editor获取输出流，写入缓存
                    if (snapshot == null) {
                        DiskLruCache.Editor editor = diskLruCache.edit(key);
                        if (editor != null) {
                            outputStream = editor.newOutputStream(0);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
                            editor.commit();
                        }
                    } else {
                        snapshot.getInputStream(0).close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
