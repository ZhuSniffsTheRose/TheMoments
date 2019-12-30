package com.thoughtworks.themoments.patterndesign.headfirst.iterator;

import java.util.ArrayList;

/**
 * Created by Zhu on 2019-12-30
 */
public class PancakeHouseMenu implements Menu {
    ArrayList<MenuItem> menuItems;


    public PancakeHouseMenu() {
        menuItems = new ArrayList();
        addItem("123", "A", true, 1.99);
        addItem("123", "B", true, 2.99);
        addItem("123", "C", true, 3.99);
        addItem("123", "D", true, 4.99);
    }

    private void addItem(String s, String ccacac, boolean b, double v) {
        MenuItem e = new MenuItem(s, ccacac, b, v);
        menuItems.add(e);
    }

    /**
     * @return
     * @deprecated
     */
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }


    @Override
    public java.util.Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }
}
