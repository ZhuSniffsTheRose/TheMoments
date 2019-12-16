package com.thoughtworks.themoments.patterndesign.command;

/**
 * Created by Zhu on 2019-12-16
 */
public class TerisMachine {
    public void toLeft() {
        System.out.println("向左");
    }


    public void toRight(){
        System.out.println("向右");
    }

    public void fastToBottom() {
        System.out.println("快速落下");
    }

    public void transform(){
        System.out.println("改变形状");
    }
}
