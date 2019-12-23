package com.thoughtworks.themoments.patterndesign.headfirst.adapter;

/**
 * Created by Zhu on 2019-12-23
 */
public class DuckAdapter implements Duck {

    Turkey turkey;

    public DuckAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
