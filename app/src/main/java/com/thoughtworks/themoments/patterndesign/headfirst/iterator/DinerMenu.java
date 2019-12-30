package com.thoughtworks.themoments.patterndesign.headfirst.iterator;

import java.util.Iterator;

/**
 * Created by Zhu on 2019-12-30
 */
public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        addItem("123", "A", true, 1.99);
        addItem("123", "B", true, 2.99);
        addItem("123", "C", true, 3.99);
        addItem("123", "D", true, 4.99);
    }

    private void addItem(String s, String d, boolean b, double v) {
        MenuItem menuItem = new MenuItem(s, d, b, v);
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("menu is full, can't add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }


    @Override
    public Iterator<MenuItem> createIterator() {
        return new DinnerMenuIterator(menuItems);
    }

    /**
     * 不要此方法，因为它会暴露我们内部的实现.   为啥就暴露了？
     *
     * @return
     * @deprecated
     */
    public MenuItem[] getMenuItems() {
        return menuItems;
    }
}
