package com.thoughtworks.themoments.patterndesign.state.practice;

/**
 * Created by Zhu on 2019-12-13
 */
public class Client {

    public static void main(String[] args) {
        // 登录
        LoginContext.getInstance().setUserState(new LoginedState());

        //注销
        LoginContext.getInstance().setUserState(new LoginoutState());


        // 我们通过设置

    }
}
