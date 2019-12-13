package com.thoughtworks.themoments.patterndesign.iterator;

/**
 * Created by Zhu on 2019-12-13
 */
public class Bos extends Leader{

    @Override
    protected void handle(int money) {
        System.out.println("Bos");
    }

    @Override
    protected int limit() {
        return Integer.MAX_VALUE;
    }
}
