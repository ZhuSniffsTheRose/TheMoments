package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 * 混合咖啡
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
