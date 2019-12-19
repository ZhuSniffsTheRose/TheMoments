package com.thoughtworks.themoments.patterndesign.headfirst.factory;

/**
 * Created by Zhu on 2019-12-19
 */
public class SimplePizzaFactory implements PizzaFactory{

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("chesse")) {
            return new CheesePizza();
        } else {
            return null;
        }
    }
}
