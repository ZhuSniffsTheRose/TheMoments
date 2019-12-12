package com.thoughtworks.themoments.imageloader;

import com.thoughtworks.themoments.imageloader.learning.ImageCache;

/**
 * Created by Zhu on 2019-12-12
 */
public class CacheFactor {

    public static <T extends ImageCache> T getCache(Class<T> clz) {
        ImageCache cache = null;
        try {
            cache = (ImageCache) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) cache;
    }
}
