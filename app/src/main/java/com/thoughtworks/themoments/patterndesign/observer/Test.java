package com.thoughtworks.themoments.patterndesign.observer;

/**
 * Created by Zhu on 2019-12-16
 */
public class Test {
    public static void main(String[] args) {
        DevTechFrontier devTechFrontier = new DevTechFrontier();
        Coder coder = new Coder("1");
        Coder coder2 = new Coder("2");

        devTechFrontier.addObserver(coder);
        devTechFrontier.addObserver(coder2);

        devTechFrontier.postNewPublication("hi good");
    }
}
