package com.thoughtworks.themoments.patterndesign.interpretor;

/**
 * Created by Zhu on 2019-12-16
 * 抽象的算术运算解释器， 为所有解释器共性的提取
 *
 * 即 翻译员 - 翻译行为
 */
public abstract class ArithmeticExpression {

    /**
     * 抽象的解析方法
     *
     * 具体的解析逻辑由具体的子类实现
     * @return 解析得到具体的值
     */
    public abstract int interpret();
}
