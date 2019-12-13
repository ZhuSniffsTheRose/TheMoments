package com.thoughtworks.themoments.patterndesign.state;

/**
 * Created by Zhu on 2019-12-13
 */
public class TvController implements PowerController, TvState {

    TvState mTvState;

    public void setTvState(TvState mTvState) {
        this.mTvState = mTvState;
    }

    @Override
    public void powerOn() {
        setTvState(new PowerOnState());
    }

    @Override
    public void powerOff() {
        setTvState(new PowerOffState());
    }


    @Override
    public void preChannel() {
        mTvState.preChannel();
    }

    @Override
    public void nextChannel() {
        mTvState.nextChannel();
    }

    @Override
    public void turnUp() {
        mTvState.turnUp();
    }

    @Override
    public void turnDown() {
        mTvState.turnDown();
    }
}
