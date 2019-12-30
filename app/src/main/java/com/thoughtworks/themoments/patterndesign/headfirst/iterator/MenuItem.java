package com.thoughtworks.themoments.patterndesign.headfirst.iterator;

/**
 * Created by Zhu on 2019-12-30
 */
public class MenuItem {
    String name;
    String description;
    /**
     * 是否素食
     */
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }
}
