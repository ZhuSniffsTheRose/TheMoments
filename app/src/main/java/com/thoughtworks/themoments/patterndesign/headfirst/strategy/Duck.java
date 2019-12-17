package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 * <p>
 * 声明每个鸭子子类都会有的行为
 * <p>
 * 其中只有外观不一样， 所以声明成了抽象
 */
public abstract class Duck {

    private Flyable flyable;
    private Quackable quackable;

    public Duck() {
        flyable = new DefaultFlyBehavior();
        quackable = new DefaultQuackBehanvior();
    }

    abstract void display();

    void swim() {
        System.out.println("swim");
    }

    public void fly() {
        flyable.fly();
    }

    public void quack() {
        quackable.quack();
    }

    public void setFlyable(Flyable flyable) {
        this.flyable = flyable;
    }

    public void setQuackable(Quackable quackable) {
        this.quackable = quackable;
    }
}
