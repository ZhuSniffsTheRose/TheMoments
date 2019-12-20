package com.thoughtworks.themoments.patterndesign.headfirst.factory;

/**
 * Created by Zhu on 2019-12-19
 *
 * 想让pizza的制作局限于此类， 而同时能让这些加盟店依然可以自由地制作该区域的风味
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        //将制作pizza的方法 从工厂方法挪到这里. 让子类各自决定如何制作pizza  ---> orderPizza 并不知道哪些
        // 实际的具体类参与进来 ---> 解耦了 ----> 原本由一个对象负责所有具体类的实例化，现在通过对 PizzaStore做了小转变，变成
        // 由一群子类来负责实例化
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        return pizza;
    }

    /**
     * 工厂方法处理对象的创建，将此行为封装在子类中，这样， PizzaStore 中的 orderPizza 就和子类对象创建的代码解耦了.
     * @param type
     * @return
     */
    protected abstract Pizza createPizza(String type);
}
