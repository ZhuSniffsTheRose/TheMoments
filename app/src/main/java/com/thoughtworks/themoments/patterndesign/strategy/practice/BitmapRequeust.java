package com.thoughtworks.themoments.patterndesign.strategy.practice;

/**
 * Created by Zhu on 2019-12-13
 */
public class BitmapRequeust implements Comparable<BitmapRequeust> {


    public int serialNum;
    LoadPolicy mLoadPolicy;

    @Override
    public int compareTo(BitmapRequeust o) {
        return mLoadPolicy.compare(this, o);
    }
}
