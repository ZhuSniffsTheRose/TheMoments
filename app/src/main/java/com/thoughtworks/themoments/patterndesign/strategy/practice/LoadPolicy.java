package com.thoughtworks.themoments.patterndesign.strategy.practice;

/**
 * Created by Zhu on 2019-12-13
 */
public interface LoadPolicy {
    public int compare(BitmapRequeust reuqest1, BitmapRequeust request2);
}
