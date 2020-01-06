package com.thoughtworks.themoments.patterndesign.headfirst.iterator.component;

/**
 * Created by Zhu on 2020-01-06
 */
public class Waitress {
    MenuComponent allmenus;

    public Waitress(MenuComponent allmenus) {
        this.allmenus = allmenus;
    }

    public void printMenu(){
        allmenus.print();
    }
}
