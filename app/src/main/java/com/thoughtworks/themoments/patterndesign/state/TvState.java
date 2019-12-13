package com.thoughtworks.themoments.patterndesign.state;

/**
 * Created by Zhu on 2019-12-13
 */
public interface TvState {
    void preChannel();

    void nextChannel();

    void turnUp();

    void turnDown();
}
