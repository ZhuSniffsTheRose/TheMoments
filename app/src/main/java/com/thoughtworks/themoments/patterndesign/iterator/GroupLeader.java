package com.thoughtworks.themoments.patterndesign.iterator;

/**
 * Created by Zhu on 2019-12-13
 */
public class GroupLeader extends Leader {

    @Override
    protected void handle(int money) {
        System.out.println("handlle GroupLeader");
    }

    @Override
    protected int limit() {
        return 1000;
    }
}
