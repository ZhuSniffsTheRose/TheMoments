package com.thoughtworks.themoments.patterndesign;

/**
 * Created by Zhu on 2019-12-11
 */
public class Tenant {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        Room room = mediator.getRoom(1, 1);

        if (room != null) {
            System.out.println("rent success ");
        }
    }
}
