package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 */
public class MallarDuckFlyBehanvior implements Flyable {

    @Override
    public void fly() {
        System.out.println("MallarDuck fly");
    }
}
