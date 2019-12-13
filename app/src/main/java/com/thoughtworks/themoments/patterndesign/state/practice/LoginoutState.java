package com.thoughtworks.themoments.patterndesign.state.practice;

/**
 * Created by Zhu on 2019-12-13
 */
public class LoginoutState implements UserState {
    @Override
    public void forword() {
        System.out.println("go Login");
    }

    @Override
    public void comment() {
        System.out.println("go Login");
    }
}
