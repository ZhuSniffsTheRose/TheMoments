package com.thoughtworks.themoments.patterndesign.headfirst.strategy;

/**
 * Created by Zhu on 2019-12-17
 */
public class Client {

    public static void main(String[] args) {
        MallarDuck mallarDuck = new MallarDuck();
        //默认的 fly 实现，如果要定制， 那么就 通过 mallarDuck 设置定制化的展示
        mallarDuck.fly();

        // 定制化实现
        mallarDuck.setFlyable(() -> System.out.println("定制化 fly"));
    }

}
