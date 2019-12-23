package com.thoughtworks.themoments.patterndesign.headfirst.command;

/**
 * Created by Zhu on 2019-12-23
 */
public class CellingFanHighCommand implements Command {
        CellingFan cellingFan;
        int preSpeed;

    public CellingFanHighCommand(CellingFan cellingFan) {
        this.cellingFan = cellingFan;
    }

    @Override
    public void execute() {
        preSpeed = cellingFan.getSpeed();
        cellingFan.high();
    }

    @Override
    public void undo() {
        if (preSpeed == cellingFan.HIGH){
            cellingFan.high();
        }else if (preSpeed == cellingFan.MEIDIUM){
            cellingFan.medium();
        }
    }
}
