package com.thoughtworks.themoments.patterndesign.headfirst.iterator.component;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Zhu on 2020-01-06
 */
public class Menu extends MenuComponent {
    ArrayList<MenuComponent> menuComponents= new ArrayList<>();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }


    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
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
    public void print() {
        System.out.println(getName());

        System.out.println(",  " + getDescription());

        System.out.println("------------");


        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()){
            MenuItem next = (MenuItem) iterator.next();
            next.print();
            // 在遍历期间，如果遇到另一个菜单对象，它的print 就会开始另一个遍历 以此类推
        }
    }
}
