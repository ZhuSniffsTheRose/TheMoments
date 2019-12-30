package com.thoughtworks.themoments.patterndesign.headfirst.factory;

/**
 * Created by Zhu on 2019-12-19
 *
 * 加盟店和创建pizza捆绑在一起的同时又保持一定的弹性
 *
 */
public class Client {
    public static void main(String[] args) {
        // 制造纽约风味的Pizza
        NYPizzaFactory nyPizzaFactory = new NYPizzaFactory();
//        PizzaStore pizzaStore = new PizzaStore(nyPizzaFactory);
//        pizzaStore.orderPizza("chesse");
//
//        // 制造普通pizza
//        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
//        PizzaStore pizzaStore1 = new PizzaStore(simplePizzaFactory);
//        pizzaStore1.orderPizza("cheese");

    }
}
