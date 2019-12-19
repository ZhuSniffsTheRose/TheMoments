package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 */
public class BigSize extends SizeDecorator {

    Beverage beverage;

    public BigSize(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public double cost() {
        return 1 + beverage.cost();
    }
}
