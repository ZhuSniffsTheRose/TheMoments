package com.thoughtworks.themoments.patterndesign.headfirst.observer;

/**
 * Created by Zhu on 2019-12-17
 */
public interface Observer {

    /**
     * 观测值直接传入观察者中 是更新状态的最直接方法，这样何时么？
     *
     * 其实，观测值的种类以及个数在未来有可能改变的
     *
     * 那么 如何将更新的状态传递给观测者
     * @param temp
     * @param humidity
     * @param pressure
     */
    void update(float temp, float humidity, float pressure);
}
