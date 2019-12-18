package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 */
public abstract class Beverage {
    /**
     * 描述每个子类
     */
    private String description;

    abstract String getDescription();

    /**
     * 每个子类都有不同的价格
     * @return
     */
    abstract int cost();
}
