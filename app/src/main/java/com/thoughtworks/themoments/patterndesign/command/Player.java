package com.thoughtworks.themoments.patterndesign.command;

/**
 * Created by Zhu on 2019-12-16
 */
public class Player {
    public static void main(String[] args) {
        // 俄罗斯方块游戏
        TerisMachine terisMachine = new TerisMachine();

        // 根据游戏构造4种命令
        LeftCommand leftCommand = new LeftCommand(terisMachine);
        RightCommand rightCommand = new RightCommand(terisMachine);
        FallCommand fallCommand = new FallCommand(terisMachine);

        // 按钮执行不同的命令
        Buttons buttons = new Buttons();
        buttons.setLeftCommand(leftCommand);
        buttons.setRightCommand(rightCommand);
        buttons.setFallCommand(fallCommand);

        // 具体按下的按钮
        buttons.toLeft();
        buttons.toRight();
        buttons.toFall();
    }
}
