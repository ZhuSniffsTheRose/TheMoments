package com.thoughtworks.themoments.patterndesign.strategy;

/**
 * Created by Zhu on 2019-12-13
 */
public class BusStrategy implements CalculateStrategy {

    @Override
    public int calculatePrice(int km) {
        return 1;
    }
}
