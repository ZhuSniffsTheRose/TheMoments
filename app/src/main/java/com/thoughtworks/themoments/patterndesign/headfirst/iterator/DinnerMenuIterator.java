package com.thoughtworks.themoments.patterndesign.headfirst.iterator;

/**
 * Created by Zhu on 2019-12-30
 */
public class DinnerMenuIterator implements java.util.Iterator<MenuItem> {
    MenuItem[] items;
    int position = 0;

    public DinnerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position >= items.length || items[position] == null;
    }

    @Override
    public MenuItem next() {
        MenuItem item = items[position];
        position++;
        return item;
    }

    @Override
    public void remove() {
        if (position < 0) {
            System.out.println("U can't remove an item until you've down at least one next()");
        }
        if (items[position - 1] != null) {
            for (int i = position - 1; i < items.length - 1; i++) {
                items[i] = items[i + 1];
            }
            items[items.length - 1] = null;
        }
    }
}


