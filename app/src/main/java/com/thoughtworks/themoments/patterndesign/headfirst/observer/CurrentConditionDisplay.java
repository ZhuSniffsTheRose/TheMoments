package com.thoughtworks.themoments.patterndesign.headfirst.observer;

/**
 * Created by Zhu on 2019-12-17
 */
public class CurrentConditionDisplay implements DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;
    /**
     * 成员变量可用于后期 反注册
     */
    private Subject subject;

    public CurrentConditionDisplay(Subject subject) {
        this.subject = subject;
        initObserver();
    }

    public void initObserver() {
        TheObserver theObserver = new TheObserver();
        subject.registerBetterObserver(theObserver);
    }

    @Override
    public void display() {
        // 显示当前观测值
        System.out.println("show data" + temperature + "  " + humidity + "  " + pressure);
    }

    class TheObserver {
        void update(float temp, float humidity, float pressure) {
            CurrentConditionDisplay.this.temperature = temp;
            CurrentConditionDisplay.this.humidity = humidity;
            CurrentConditionDisplay.this.pressure = pressure;
            display();
        }
    }
}
