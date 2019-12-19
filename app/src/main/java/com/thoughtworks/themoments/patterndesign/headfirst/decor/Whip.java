package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 3;
    }

    @Override
    public String getDescription() {
        return "  ";
    }
}
