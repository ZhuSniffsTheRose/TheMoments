package com.thoughtworks.themoments.patterndesign.headfirst.command;

/**
 * Created by Zhu on 2019-12-20
 */
public class SimpleRemoteControl {
    Command[] onCommands;
    Command[] offCommands;

    Command lastCommand;

    public SimpleRemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        lastCommand = noCommand;
    }

    public void setSlot(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPressed(int slot) {
        onCommands[slot].execute();
        lastCommand = onCommands[slot];
    }

    public void offButtonWasPressed(int slot) {
        offCommands[slot].execute();
        lastCommand = onCommands[slot];
    }

    public void undo() {
        lastCommand.undo();
    }

}