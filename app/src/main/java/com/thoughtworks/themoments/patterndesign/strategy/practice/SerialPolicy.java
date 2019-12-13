package com.thoughtworks.themoments.patterndesign.strategy.practice;

/**
 * Created by Zhu on 2019-12-13
 */
public class SerialPolicy implements LoadPolicy {

    @Override
    public int compare(BitmapRequeust reuqest1, BitmapRequeust request2) {
        return reuqest1.serialNum - request2.serialNum;
    }
}
