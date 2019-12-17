package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 */
public class RedHeadDuck extends Duck implements Flyable, Quackable{
    @Override
    void display() {
        System.out.println("红头");
    }

    @Override
    public void fly() {
        System.out.println("RedHeadDuck fly");
    }

    @Override
    public void quack() {
        System.out.println("RedHeadDuck fly");
    }
}
