package com.thoughtworks.themoments.patterndesign.strategy;

/**
 * Created by Zhu on 2019-12-13
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
