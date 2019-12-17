package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 */
public class MallarDuck extends Duck  implements Flyable,Quackable{
    @Override
    void display() {
        System.out.println("绿色");
    }

    @Override
    public void fly() {
        System.out.println("MallarDuck fly");
    }

    @Override
    public void quack() {
        System.out.println("MallarDuck quack");
    }
}
