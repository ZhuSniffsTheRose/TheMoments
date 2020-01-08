package com.thoughtworks.themoments.patterndesign.headfirst.state.better;

public interface State {
 
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
	
	public void refill();
}
