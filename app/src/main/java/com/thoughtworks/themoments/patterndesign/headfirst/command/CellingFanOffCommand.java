package com.thoughtworks.themoments.patterndesign.headfirst.command;

/**
 * Created by Zhu on 2019-12-23
 */
public class CellingFanOffCommand implements Command {
    CellingFan cellingFan;
    int preSpeed;


    public CellingFanOffCommand(CellingFan cellingFan) {
        this.cellingFan = cellingFan;
    }

    @Override
    public void execute() {
        cellingFan.off();
        preSpeed = CellingFan.OFF;
    }

    @Override
    public void undo() {
        if (preSpeed == cellingFan.HIGH) {
            cellingFan.high();
        } else if (preSpeed == cellingFan.MEIDIUM) {
            cellingFan.medium();
        }
    }
}
