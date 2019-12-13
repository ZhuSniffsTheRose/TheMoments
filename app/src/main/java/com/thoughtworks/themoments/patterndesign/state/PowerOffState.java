package com.thoughtworks.themoments.patterndesign.state;

/**
 * Created by Zhu on 2019-12-13
 */
public class PowerOffState implements TvState {
    @Override
    public void preChannel() {
        System.out.println("invalid");
    }

    @Override
    public void nextChannel() {
        System.out.println("invalid");
    }

    @Override
    public void turnUp() {
        System.out.println("invalid");
    }

    @Override
    public void turnDown() {
        System.out.println("invalid");
    }
}
