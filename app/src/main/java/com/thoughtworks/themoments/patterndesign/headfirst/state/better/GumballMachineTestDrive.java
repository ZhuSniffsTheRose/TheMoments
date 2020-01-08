package com.thoughtworks.themoments.patterndesign.headfirst.state.better;


/**
 * 状态模式，在多状态对象中，声明这些状态， 并将其中一个状态声明为初始状态，
 * 当调用行为时，会改变状态， 即把多状态对象当前的状态对象更新，然后执行对应的方法时，就直接拿着当前状态对象进行方法执行.
 *
 *
 * 状态模式：多状态的对象 ---> 各个状态对象 ----> 执行改变状态的方法时调用状态对象的方法 ---> 执行状态实际逻辑+ 改变多状态对象的状态对象
 */
public class GumballMachineTestDrive {

	public static void main(String[] args) {
		GumballMachine gumballMachine =
				new GumballMachine(10);

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
}
