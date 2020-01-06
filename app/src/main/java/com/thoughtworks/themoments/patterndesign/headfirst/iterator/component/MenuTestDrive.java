package com.thoughtworks.themoments.patterndesign.headfirst.iterator.component;

/**
 * Created by Zhu on 2020-01-06
 */
public class MenuTestDrive {

    public static void main(String[] args) {
        MenuComponent menu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent menu1 = new Menu("PANCAKE HOUSE MENU 1", "Breakfast");
        MenuComponent menu2 = new Menu("PANCAKE HOUSE MENU 2", "Breakfast");
        MenuComponent menu3 = new Menu("PANCAKE HOUSE MENU 2", "Breakfast");


        MenuComponent allMenus = new Menu("All Menu", "All menus combined");
        allMenus.add(menu);
        allMenus.add(menu1);
        allMenus.add(menu2);

        // 在菜单上加另一个菜单
        menu2.add(menu3);


        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();

    }

}
