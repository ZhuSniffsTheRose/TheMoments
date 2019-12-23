package com.thoughtworks.themoments.patterndesign.headfirst.adapter;

/**
 * Created by Zhu on 2019-12-23
 */
public class MallarDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("quack");
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }
}
