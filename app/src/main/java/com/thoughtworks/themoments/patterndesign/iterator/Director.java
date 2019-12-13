package com.thoughtworks.themoments.patterndesign.iterator;

/**
 * Created by Zhu on 2019-12-13
 */
public class Director extends Leader {
    @Override
    protected void handle(int money) {
        System.out.println("Director");
    }

    @Override
    protected int limit() {
        return 2000;
    }
}
