package com.thoughtworks.themoments.patterndesign.headfirst.iterator;

/**
 * Created by Zhu on 2019-12-30
 */
public class Test {
    public static void main(String[] args) {
        Alice alice = new Alice(new PancakeHouseMenu(), new DinerMenu());
        alice.printMenu();
    }
}
