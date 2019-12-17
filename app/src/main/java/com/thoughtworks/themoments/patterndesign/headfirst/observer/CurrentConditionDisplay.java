package com.thoughtworks.themoments.patterndesign.headfirst.observer;

/**
 * Created by Zhu on 2019-12-17
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;
    /**
     * 成员变量可用于后期 反注册
     */
    private Subject subject;

    public CurrentConditionDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void display() {
        // 显示当前观测值
        System.out.println("show data" + temperature + "  " + humidity + "  " + pressure);
    }

    /**
     * 这三个参数，是observable将其所有状态推过来，不一定是observer都需要的, 所以应该提供需要啥，给啥===>即 observer去拉取.
     * @param temp
     * @param humidity
     * @param pressure
     */
    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
