package com.thoughtworks.themoments.patterndesign.headfirst.observer.java;

import com.thoughtworks.themoments.patterndesign.headfirst.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Zhu on 2019-12-17
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;


    @Override
    public void display() {
        // 显示当前观测值
        System.out.println("show data" + temperature + "  " + humidity + "  " + pressure);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof WeatherData) {
            WeatherData arg1 = (WeatherData) arg;
            this.temperature = arg1.getTemperature();
            this.humidity = arg1.getHumidity();
            this.pressure = arg1.getPressure();
            display();
        }
    }
}
