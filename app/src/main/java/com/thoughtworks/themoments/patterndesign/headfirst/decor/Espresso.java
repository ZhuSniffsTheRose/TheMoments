package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 * 浓咖啡
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = " Espresso ";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
