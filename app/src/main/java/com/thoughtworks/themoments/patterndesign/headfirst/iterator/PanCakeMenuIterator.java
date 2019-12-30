package com.thoughtworks.themoments.patterndesign.headfirst.iterator;

import java.util.ArrayList;

/**
 * Created by Zhu on 2019-12-30
 */
public class PanCakeMenuIterator implements Iterator {
    ArrayList<MenuItem> items;

    int index = 0;

    public PanCakeMenuIterator(ArrayList<MenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return index >= items.size() || items.get(index) == null;
    }

    @Override
    public Object next() {
        MenuItem menuItem = items.get(index);
        index++;
        return menuItem;
    }
}
