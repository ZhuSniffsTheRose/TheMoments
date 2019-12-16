package com.thoughtworks.themoments.patterndesign.command;

/**
 * Created by Zhu on 2019-12-16
 */
public class LeftCommand implements Command {

    private TerisMachine machine;

    public LeftCommand(TerisMachine machine) {
        this.machine = machine;
    }

    @Override
    public void execute() {
        machine.toLeft();
    }
}
