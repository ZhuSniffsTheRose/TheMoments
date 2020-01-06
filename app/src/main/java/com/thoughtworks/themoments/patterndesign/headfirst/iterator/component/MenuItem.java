package com.thoughtworks.themoments.patterndesign.headfirst.iterator.component;

/**
 * Created by Zhu on 2020-01-06
 */
public class MenuItem extends MenuComponent {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }


    @Override
    public void print() {
        System.out.println("     " + getName());
        if (isVegetarian()){
            System.out.println("(V)");
        }

        System.out.println(", " + getPrice());
        System.out.println("    --" + getDescription());
    }
}
