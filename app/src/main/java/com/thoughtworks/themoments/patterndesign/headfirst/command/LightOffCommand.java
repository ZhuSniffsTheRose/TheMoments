package com.thoughtworks.themoments.patterndesign.headfirst.command;

/**
 * Created by Zhu on 2019-12-23
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
