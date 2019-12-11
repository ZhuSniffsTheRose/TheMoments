package com.thoughtworks.themoments.patterndesign.singleton;

/**
 * Created by Zhu on 2019-12-11
 */
public class Singleton {

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }


    private static class SingletonHolder {
        private static final Singleton singleton = new Singleton();
    }
}
