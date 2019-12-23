package com.thoughtworks.themoments.patterndesign.headfirst.command;

/**
 * Created by Zhu on 2019-12-20
 * 命令接口
 */
public interface Command {
    public void execute();


    /**
     * 最后一次执行反转
     */
    public void undo();
}
