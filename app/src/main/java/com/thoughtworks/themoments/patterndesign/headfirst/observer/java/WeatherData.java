package com.thoughtworks.themoments.patterndesign.headfirst.observer.java;

import java.util.Observable;


/**
 * 系统内置的 Observable中 setChange(),用以标记状态已经改变的事实. 为了让 notifyObservers 被调用时，通过此状态判断是否应该下发给 Observer
 *
 * 避免频繁更新.
 *
 */
public class WeatherData extends Observable {
    /**
     * 三个值的变化来自系统返回
     * <p>
     * 系统会自动设置，我们不用关心
     */
    private float temperature;
    private float humidity;
    private float pressure;

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }


    public void setMeasurements(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }


    /**
     * 数据更新，此方法就会被调用
     */
    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }
}