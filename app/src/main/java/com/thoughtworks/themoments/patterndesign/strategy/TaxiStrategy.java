package com.thoughtworks.themoments.patterndesign.strategy;

/**
 * Created by Zhu on 2019-12-13
 */
public class TaxiStrategy implements CalculateStrategy {
    @Override
    public int calculatePrice(int km) {
        return 0;
    }
}
