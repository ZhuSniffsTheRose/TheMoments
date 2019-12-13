package com.thoughtworks.themoments.patterndesign.state.practice;

/**
 * Created by Zhu on 2019-12-13
 */
public class LoginedState implements UserState {
    @Override
    public void forword() {
        System.out.println("forword");
    }

    @Override
    public void comment() {
        System.out.println("comment");
    }
}
