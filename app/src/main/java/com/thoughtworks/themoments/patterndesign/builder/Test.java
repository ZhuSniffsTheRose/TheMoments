package com.thoughtworks.themoments.patterndesign.builder;

/**
 * Created by Zhu on 2019-12-11
 * 使得同样的构建过程可以创建不同的表示
 *
 * 不理解 builder 的设计模式精髓。
 *
 * 我完全可以通过待 build 的对象，将其 set 方法 返回 this， 然后在其构造方法中设置默认值。
 * 何必还要有一个 builder
 *
 * 这个设计模式 又是如何体现solid设计原则的呢？
 *
 */
public class Test {


    public static void main(String[] args) {
        Computer.BuilderComputer builderComputer = new Computer.BuilderComputer();
        Computer build = builderComputer.buildCPU("").buildKey("").buildScreen("").build();
    }

}
