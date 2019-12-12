package com.thoughtworks.themoments.patterndesign.prototype.practice;

/**
 * Created by Zhu on 2019-12-12
 */
public class LoginImpl implements Login {
    @Override
    public void login() {
        User user = new User();
        user.age = 22;
        user.name = "Mr.Zhu";
        user.address = new Address("成都市","锦江区", "桐梓林");


    }
}
