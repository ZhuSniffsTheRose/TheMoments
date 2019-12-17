package com.thoughtworks.themoments.patterndesign.headfirst.observer;

/**
 * Created by Zhu on 2019-12-17
 */
public class ThirdPartyDisplay implements Observer, DisplayElement{


    @Override
    public void display() {
        // 显示基于观测值的其他内容
    }

    @Override
    public void update(float temp, float humidity, float pressure) {

    }
}
