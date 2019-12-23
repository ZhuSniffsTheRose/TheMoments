package com.thoughtworks.themoments.patterndesign.headfirst.adapter;

/**
 * Created by Zhu on 2019-12-23
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble");
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }
}
