package com.thoughtworks.themoments.patterndesign.interpretor;

/**
 * Created by Zhu on 2019-12-16
 */
public class NumExpression extends ArithmeticExpression {

    private int num;

    public NumExpression(int num) {
        this.num = num;
    }

    @Override
    public int interpret() {
        return num;
    }
}
