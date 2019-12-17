package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 */
public class DefaultQuackBehanvior implements Quackable {
    @Override
    public void quack() {
        System.out.println("DefaultQuackBehanvior quack");
    }
}
