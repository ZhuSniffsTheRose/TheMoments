package com.thoughtworks.themoments.imageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.thoughtworks.themoments.R;
import com.thoughtworks.themoments.imageloader.cache.ImageCache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Zhu on 2019-11-26
 */
public class ImageLoader {
    private static final String TAG = "ImageLoader";
    private final Context context;
    private final ImageCache mImageCache;

    public ImageLoader(Context context) {
        this.context = context;
        mImageCache = new ImageCache(context);
    }

    public void loadImg(String url, ImageView imageView) {
        //先从内存缓存中获取，没有执行异步任务从硬盘缓存或者网络获取
        final Bitmap bitmap = mImageCache.getBitmapFromMemCache(url);
        if (bitmap != null) {
            Log.i(TAG, "get bitmap " + url + "from memeory cache.");
            imageView.setImageBitmap(bitmap);
        } else {
            //检查复用的imageView当前是否相关的异步任务正在获取图片，如果获取的不是同一张则取消
            if (cancelPotentialAsyncTask(url, imageView)) {
                //创建并执行异步任务，并将任务关联到默认图的Drawble，设置给imageView
                final DownLoadBitmapTask downLoadBitmapTask = new DownLoadBitmapTask(imageView);
                final AsyncDrawable asyncDrawable = new AsyncDrawable(context.getResources(), BitmapFactory.
                        decodeResource(context.getResources(), R.mipmap.ic_launcher), downLoadBitmapTask);
                imageView.setImageDrawable(asyncDrawable);
                downLoadBitmapTask.execute(url);
            }
        }
    }

    /**
     * 检查复用的imageView当前是否相关的异步任务正在获取图片，如果获取的不是同一张则取消，创建新的task获取你想要的图片；
     * 如果是同一张，则继续执行任务获取
     *
     * @param url       当前获取图片url
     * @param imageView 当前使用imageView
     * @return 是否取消了重复任务
     */
    public static boolean cancelPotentialAsyncTask(String url, ImageView imageView) {
        final DownLoadBitmapTask bitmapWorkerTask = getBitmapTaskByImageView(imageView);
        if (bitmapWorkerTask != null) {
            final String bitmapUrl = bitmapWorkerTask.url;
            //不是同一张图片，则取消原来的任务，创建新的任务获取
            if (bitmapUrl == null || bitmapUrl != url) {
                bitmapWorkerTask.cancel(true);
            }
            //如果加载的是同一个图片，则继续异步获取，不创建新的任务下载
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取imageview当前对应的异步下载任务
     *
     * @param imageView image对象
     * @return 关联的异步任务
     */
    private static DownLoadBitmapTask getBitmapTaskByImageView(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    /**
     * 异步Drawable，关联下载该图片的异步任务
     */
    static class AsyncDrawable extends BitmapDrawable {
        //下载位图对应的异步任务
        private final WeakReference<DownLoadBitmapTask> downLoadBitmapTaskWeakReference;

        public AsyncDrawable(Resources res, Bitmap bitmap, DownLoadBitmapTask downLoadBitmapTask) {
            super(res, bitmap);
            downLoadBitmapTaskWeakReference = new WeakReference<>(downLoadBitmapTask);
        }

        public DownLoadBitmapTask getBitmapWorkerTask() {
            return downLoadBitmapTaskWeakReference.get();
        }
    }

    /**
     * 下载位图异步任务
     */
    private class DownLoadBitmapTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewWeakReference;
        private Bitmap bitmap;
        private String url;

        private DownLoadBitmapTask(ImageView imageView) {
            this.imageViewWeakReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                //先从硬盘缓存获取，否则从服务器获取，成功后添加到内存和硬盘缓存
                url = params[0];
                bitmap = mImageCache.getBitmapFromDiskCache(url);
                if (bitmap == null) {
                    bitmap = downloadBitmapFromUrl(url);
                    Log.i(TAG, "download bitmap " + url + "from server.");
                } else {
                    Log.i(TAG, "get bitmap " + url + "from disk cache.");
                }
                mImageCache.addBitmapToCache(url, bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            //如果activity退出，或者配合发生改变重建时，imageView不一定存在，故需要检测
            if (imageViewWeakReference != null && bitmap != null) {
                final ImageView imageView = imageViewWeakReference.get();
                final DownLoadBitmapTask downLoadBitmapTask = getBitmapTaskByImageView(imageView);
                if (this == downLoadBitmapTask && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    /**
     * 从服务端下载位图
     *
     * @param url url
     * @return 位图
     * @throws IOException
     */
    private Bitmap downloadBitmapFromUrl(String url) throws IOException {
        Bitmap bitmap = null;
        InputStream is = null;

        try {
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();

            conn.setReadTimeout(1000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int response = conn.getResponseCode();
            if (response == 200) {
                is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return bitmap;
    }
}