package com.thoughtworks.themoments.patterndesign.command;

/**
 * Created by Zhu on 2019-12-16
 */
public class RightCommand implements Command{
    private TerisMachine machine;

    public RightCommand(TerisMachine machine) {
        this.machine = machine;
    }


    @Override
    public void execute() {
        machine.toRight();
    }
}
