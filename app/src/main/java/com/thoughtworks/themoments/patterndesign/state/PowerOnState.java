package com.thoughtworks.themoments.patterndesign.state;

/**
 * Created by Zhu on 2019-12-13
 */
public class PowerOnState implements TvState {
    @Override
    public void preChannel() {
        System.out.println("preChannel");
    }

    @Override
    public void nextChannel() {
        System.out.println("nextChannel");
    }

    @Override
    public void turnUp() {
        System.out.println("turnUp");
    }

    @Override
    public void turnDown() {
        System.out.println("turnDown");
    }
}
