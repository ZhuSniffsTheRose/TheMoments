package com.thoughtworks.themoments.patterndesign.headfirst.state;

/**
 * Created by Zhu on 2020-01-06
 */
public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);




        // 增加需求，当执行 turnCrank 时，有10%的机率掉下两颗糖果

    }
}
