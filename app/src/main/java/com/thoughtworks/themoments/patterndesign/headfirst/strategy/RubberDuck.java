package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 */
public class RubberDuck extends Duck implements Quackable {

    @Override
    void display() {
        System.out.println("RedHeadDuck display");
    }

    @Override
    public void quack() {
        System.out.println("RedHeadDuck quack");
    }
}
