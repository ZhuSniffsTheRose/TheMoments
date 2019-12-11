package com.thoughtworks.themoments.patterndesign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhu on 2019-12-11
 */
public class Mediator {
    private List<Room> mRooms = new ArrayList<>();


    public Mediator() {
        for (int i = 0; i < 19; i++) {
            mRooms.add(new Room(14 + i, (14 + i) * 150));
        }
    }

    public List<Room> getmRooms() {
        return mRooms;
    }


    public Room getRoom(float area, float price) {
        for (Room room : mRooms) {
            if (room.getArea() == area && room.getPrice() == price) {
                return room;
            }
        }
        return null;
    }


}
