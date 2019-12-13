package com.thoughtworks.themoments.patterndesign.state.practice;

/**
 * Created by Zhu on 2019-12-13
 */
public class LoginContext implements UserState {

    private UserState mUserState;

    public static LoginContext getInstance() {
        return LoginContextSingletonHolder.sLoginContext;
    }

    @Override
    public void forword() {
        mUserState.forword();
    }

    @Override
    public void comment() {
        mUserState.comment();
    }

    static class LoginContextSingletonHolder {
        static final LoginContext sLoginContext = new LoginContext();
    }

    public void setUserState(UserState mUserState) {
        this.mUserState = mUserState;
    }

}
