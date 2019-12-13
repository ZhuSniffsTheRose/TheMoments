package com.thoughtworks.themoments.patterndesign.iterator;

/**
 * Created by Zhu on 2019-12-13
 */
public class XiaoMin {

    public static void main(String[] args) {
        GroupLeader groupLeader = new GroupLeader();
        Director director = new Director();
        Bos bos = new Bos();
        groupLeader.nextHandler = director;
        director.nextHandler = bos;
        groupLeader.handle(10000);
    }
}
