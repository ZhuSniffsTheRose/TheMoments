package com.thoughtworks.themoments.patterndesign.interpretor;

/**
 * Created by Zhu on 2019-12-16
 */
public class Client {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("1 + 2 + 3 + 4");
        System.out.println(calculator.calulate());
    }
}
