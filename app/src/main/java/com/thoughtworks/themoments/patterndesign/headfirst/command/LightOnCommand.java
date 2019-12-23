package com.thoughtworks.themoments.patterndesign.headfirst.command;

/**
 * Created by Zhu on 2019-12-20
 *
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
