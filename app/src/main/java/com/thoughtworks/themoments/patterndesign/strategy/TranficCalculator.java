package com.thoughtworks.themoments.patterndesign.strategy;

/**
 * Created by Zhu on 2019-12-13
 *
 * 将可能变化的行为封装成一个抽象， 然后在运行时，传入具体的实现. 利用多态，动态的进行 行为变化.
 */
public class TranficCalculator {


    private CalculateStrategy mStrategy;


    public static void main(String[] args) {
        TranficCalculator client = new TranficCalculator();
        client.setStrategy(new BusStrategy());
        System.out.println("price :" + client.getPrice(16));
    }


    public int getPrice(int km) {
        return mStrategy.calculatePrice(km);
    }

    public void setStrategy(CalculateStrategy mStrategy) {
        this.mStrategy = mStrategy;
    }
}
