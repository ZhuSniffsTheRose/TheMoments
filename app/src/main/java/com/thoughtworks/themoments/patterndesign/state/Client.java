package com.thoughtworks.themoments.patterndesign.state;

/**
 * Created by Zhu on 2019-12-13
 *
 *  大量的方法在有多个状态. 我们就将所有方法抽象，然后不同的状态有不同的实现类. 然后调用方法前，设置对应的状态. 从而实现在调用方法时，使用的是
 *  特定实现类中的方法.
 */
public class Client {

    public static void main(String[] args) {
        TvController tvController = new TvController();
        tvController.powerOn();
        tvController.preChannel();
        tvController.nextChannel();
        tvController.turnDown();
        tvController.powerOff();
    }
}
