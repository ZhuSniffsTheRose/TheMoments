package com.thoughtworks.themoments.patterndesign.observer;

import androidx.annotation.NonNull;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Zhu on 2019-12-16
 */
public class Coder implements Observer {

    private String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Hi" + name + "内容更新，" + o + " 内容：" + arg);
    }

    @NonNull
    @Override
    public String toString() {
        return "码农 " + name;
    }
}
