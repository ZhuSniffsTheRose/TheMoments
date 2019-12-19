package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 */
public class StarbuzzCoffee {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        Mocha mocha = new Mocha(beverage);
        Whip whip = new Whip(beverage);


        Beverage houseBlend = new HouseBlend();
        Mocha mocha1 = new Mocha(houseBlend);
        Whip whip1 = new Whip(houseBlend);


    }
}
