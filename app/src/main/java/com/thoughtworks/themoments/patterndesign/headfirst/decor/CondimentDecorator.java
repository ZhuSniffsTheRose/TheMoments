package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 * Condiment -- 调味品
 * Beverage -- 饮料
 */
public abstract class CondimentDecorator extends Beverage {
   @Override
   public abstract String getDescription();
}
