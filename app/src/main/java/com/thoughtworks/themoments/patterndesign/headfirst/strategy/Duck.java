package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 * <p>
 * 声明每个鸭子子类都会有的行为
 * <p>
 * 其中只有外观不一样， 所以声明成了抽象
 */
public abstract class Duck {
    void quack() {
        System.out.println("dadadada");
    }

    abstract void display();

    void swim() {
        System.out.println("swim");
    }

    void fly() {
        System.out.println("鸭子飞起来");
    }
}
