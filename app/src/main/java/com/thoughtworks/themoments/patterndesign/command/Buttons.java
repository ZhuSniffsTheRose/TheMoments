package com.thoughtworks.themoments.patterndesign.command;

/**
 * Created by Zhu on 2019-12-16
 */
public class Buttons {
    private LeftCommand leftCommand;
    private RightCommand rightCommand;
    private FallCommand fallCommand;

    public void setLeftCommand(LeftCommand leftCommand) {
        this.leftCommand = leftCommand;
    }

    public void setRightCommand(RightCommand rightCommand) {
        this.rightCommand = rightCommand;
    }

    public void setFallCommand(FallCommand fallCommand) {
        this.fallCommand = fallCommand;
    }

    public void toLeft(){
        leftCommand.execute();
    }

    public void toRight(){
        rightCommand.execute();
    }

    public void toFall(){
        fallCommand.execute();
    }
}
