package com.thoughtworks.themoments.patterndesign.headfirst.iterator.component;

/**
 * Created by Zhu on 2020-01-06
 *
 *  如果添加的 菜单过多， 就会 调用 多个 菜单的 print 方法.
 *
 *
 * 叶节点 和 组合 节点提供一个公共接口
 *
 * 组件中声明的方法有些只对菜单项有意义，而有些只对菜单有意义，默认实现抛出异常.
 *
 * 这个组件不就扮演了两个角色么？
 *
 *
 *
 */
public class MenuComponent {

    /*----------------- 组合 的方法--------------------*/

    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }
    /*----------------- 组合 的方法--------------------*/



    /*----------------- 菜单项 的方法--------------------*/

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }
    /*----------------- 菜单项 的方法--------------------*/


    /*-----------------菜单和菜单项 同时持有--------------------*/

    public void print() {
        throw new UnsupportedOperationException();
    }

    /*-----------------菜单和菜单项 同时持有--------------------*/
}
