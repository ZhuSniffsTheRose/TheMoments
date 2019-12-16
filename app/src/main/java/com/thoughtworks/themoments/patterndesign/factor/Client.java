package com.thoughtworks.themoments.patterndesign.factor;

/**
 * Created by Zhu on 2019-12-12
 */
public class Client {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product = factory.createProduct(ConcreteProductB.class);
        product.method();
    }
}
