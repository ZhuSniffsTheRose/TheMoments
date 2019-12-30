package com.thoughtworks.themoments.patterndesign.headfirst.iterator;

/**
 * Created by Zhu on 2019-12-30
 */
public class Alice {
    Menu pancakeHouseMenu;
    Menu dinerMenu;


    public Alice(Menu pancakeHouseMenu, Menu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        java.util.Iterator iterator = pancakeHouseMenu.createIterator();
        printMenu(iterator);

        java.util.Iterator iterator1 = dinerMenu.createIterator();
        printMenu(iterator1);
    }


    public void printMenu(java.util.Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem next = (MenuItem) iterator.next();
            System.out.println(next.toString());
        }
    }

    public void printBreakfastMenu() {

    }

    public void printLunchMenu() {

    }

    public void printVegetarianMenu() {

    }

    public boolean isItemVegetarian(String name) {
        return true;
    }

}
