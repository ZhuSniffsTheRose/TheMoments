package com.thoughtworks.themoments.patterndesign.headfirst.state;

/**
 * Created by Zhu on 2020-01-06
 */
public class GumballMachine {
    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;

    int state = SOLD_OUT;
    int count = 0;


    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    // 投币
    public void insertQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("You can't insert another quarter");
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("You insert a quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You can't insert a quarter, the machine is sold out");
        } else if (state == SOLD) {
            System.out.println("Please wait, we're already giving you a  gumnall");
        }
    }


    public void ejectQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("Quarter return");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("You haven't inserted a quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You can't eject.  you haven't inserted a quarter yet");
        } else if (state == SOLD) {
            System.out.println("Sorry, you already turned the crank");
        }
    }


    public void turnCrank() {
        if (state == SOLD) {
            System.out.println("turnCrank SOLD  ");
        } else if (state == NO_QUARTER) {
            System.out.println("turnCrank NO_QUARTER");
        } else if (state == SOLD_OUT) {
            System.out.println("turnCrank SOLD_OUT");
        }else if (state == HAS_QUARTER) {
            System.out.println("turnCrank HAS_QUARTER");
            state = SOLD;
            dispense();
        }
    }

    private void dispense() {
        if (state == SOLD) {
            System.out.println("dispense SOLD  ");
            if (count == 0){
                state = SOLD_OUT;
                System.out.println("out of gumball");
            }else {
                state = NO_QUARTER;
            }
        } else if (state == NO_QUARTER) {
            System.out.println("dispense NO_QUARTER");
        } else if (state == SOLD_OUT) {
            System.out.println("dispense SOLD_OUT");
        }else if (state == HAS_QUARTER) {
            System.out.println("dispense HAS_QUARTER");
        }
    }

}


