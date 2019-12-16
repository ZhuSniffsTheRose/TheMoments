package com.thoughtworks.themoments.patterndesign.command;

/**
 * Created by Zhu on 2019-12-16
 */
public class FallCommand implements Command {

    private TerisMachine machine;

    public FallCommand(TerisMachine machine) {
        this.machine = machine;
    }

    @Override
    public void execute() {
        machine.fastToBottom();
    }
}
