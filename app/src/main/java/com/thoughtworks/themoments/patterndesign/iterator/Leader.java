package com.thoughtworks.themoments.patterndesign.iterator;

/**
 * Created by Zhu on 2019-12-13
 */
public abstract class Leader {
    protected Leader nextHandler;

    public final void handlerRequest(int money) {
        if (money <= limit()) {
            handle(money);
        } else {
            nextHandler.handlerRequest(money);
        }
    }

    protected abstract void handle(int money);

    protected abstract int limit();
}
