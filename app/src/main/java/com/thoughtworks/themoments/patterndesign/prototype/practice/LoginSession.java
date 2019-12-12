package com.thoughtworks.themoments.patterndesign.prototype.practice;

/**
 * Created by Zhu on 2019-12-12
 * 我们可以通过 getsLoginSession().getLoginedUser().address = new Address(...)修改 user 信息.
 */
public class LoginSession {
    static LoginSession sLoginSession = null;
    private User loginedUser;

    private LoginSession() {
    }

    public static LoginSession getsLoginSession() {
        if (sLoginSession == null) {
            sLoginSession = new LoginSession();
        }
        return sLoginSession;
    }

    void setLoginedUser(User user) {
        loginedUser = user;
    }

    public User getLoginedUser() throws CloneNotSupportedException {
        return loginedUser.clone();
    }
}
