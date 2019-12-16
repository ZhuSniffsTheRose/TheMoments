package com.thoughtworks.themoments.patterndesign.interpretor;

import java.util.Stack;
/**
 * Created by Zhu on 2019-12-16
 */
public class Calculator {
    private Stack<ArithmeticExpression> mExpStack = new Stack<>();

    public Calculator(String expression) {
        ArithmeticExpression expression1, expression2;
        String[] element = expression.split(" ");

        for (int i = 0; i < element.length; i++) {

            switch (element[i].charAt(0)) {
                case '+':
                    // 将栈中的解释器弹出作为运算符号左边的解释器
                    expression1 = mExpStack.pop();
                    //同时将运算符号数组下标下一个元素构造为一个数字解释器
                    expression2 = new NumExpression((Integer.valueOf(element[++i])));

                    mExpStack.push(new AdditionExpression(expression1, expression2));
                    break;

                default:
                    mExpStack.push(new NumExpression((Integer.valueOf(element[i]))));
                    break;
            }


        }
    }


    public int calulate() {
        return mExpStack.pop().interpret();
    }
}
