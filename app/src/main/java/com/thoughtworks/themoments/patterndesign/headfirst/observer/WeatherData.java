package com.thoughtworks.themoments.patterndesign.headfirst.observer;

import java.util.ArrayList;

/**
 * Created by Zhu on 2019-12-17
 * <p>
 * 观察者模式： Observable管理某些数据， 当 Observable 的数据改变，就会通知观察者， observer 会在数据改变时接收通知.
 * <p>
 * 在 observable 中可以动态添加/删除 observer.
 */
public class WeatherData implements Subject {
    /**
     * 三个值的变化来自系统返回
     * <p>
     * 系统会自动设置，我们不用关心
     */
    private float temperature;
    private float humidity;
    private float pressure;

    private ArrayList<Observer> observers;


    public WeatherData() {
        observers = new ArrayList<>();
    }

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
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void registerBetterObserver(CurrentConditionDisplay.TheObserver observer) {
        observer.update(getTemperature(), getHumidity(), getPressure());
    }

    @Override
    public void unregisterObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i > 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(getTemperature(), getHumidity(), getPressure());
        }
    }

}
