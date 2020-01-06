package com.thoughtworks.themoments.patterndesign.headfirst.iterator.component;

/**
 * Created by Zhu on 2020-01-06
 */
public class PancakeMenu extends Menu {


    public PancakeMenu(String name, String description) {
        super(name, description);
        addItem("123", "A", true, 1.99);
        addItem("123", "B", true, 2.99);
        addItem("123", "C", true, 3.99);
        addItem("123", "D", true, 4.99);
    }

    private void addItem(String s, String ccacac, boolean b, double v) {
        Menu menu = new Menu(s, ccacac);
        menuComponents.add(menu);
    }



    public java.util.Iterator<MenuComponent> createIterator() {
        return menuComponents.iterator();
    }
}
