package com.thoughtworks.themoments.patterndesign.factor;

/**
 * Created by Zhu on 2019-12-12
 */
public abstract class Factory {
    public abstract <T extends Product> T createProduct(Class<T> clz);
}
