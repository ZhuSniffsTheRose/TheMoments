package com.thoughtworks.themoments.patterndesign.headfirst.observer;

/**
 * Created by Zhu on 2019-12-17
 */
public interface Subject {

    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    void notifyObservers();
}
