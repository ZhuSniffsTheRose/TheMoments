package com.thoughtworks.themoments.patterndesign.prototype.practice;

import androidx.annotation.NonNull;

/**
 * Created by Zhu on 2019-12-12
 * <p>
 * LoginSession 保存用户的登录信息， 用于 App 其他模块登录校验，用户个人信息展示。
 * 这些信息，在客户端程序是不允许修改的，而需要在其他模块调用，
 * so，需要开放已登录用户信息的访问接口。
 */
public class User implements Cloneable {
    public int age;
    public String name;
    public String phoneNum;
    public Address address;

    @NonNull
    @Override
    protected User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.address = address.clone();
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address=" + address +
                '}';
    }
}
