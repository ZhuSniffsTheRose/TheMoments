package com.thoughtworks.themoments.patterndesign.observer;

import java.util.Observable;

/**
 * Created by Zhu on 2019-12-16
 */
public class DevTechFrontier extends Observable {

    public void postNewPublication(String content) {
        setChanged();
        notifyObservers(content);
    }



}
