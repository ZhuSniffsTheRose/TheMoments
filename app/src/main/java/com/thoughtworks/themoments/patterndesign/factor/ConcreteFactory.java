package com.thoughtworks.themoments.patterndesign.factor;

/**
 * Created by Zhu on 2019-12-12
 */
public class ConcreteFactory extends Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}
