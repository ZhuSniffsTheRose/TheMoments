package com.thoughtworks.themoments.patterndesign;

/**
 * Created by Zhu on 2019-12-11
 */
public class Room {
    private float area;
    private float price;

    public Room(float area, float price) {
        this.area = area;
        this.price = price;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Room{" +
                "area=" + area +
                ", price=" + price +
                '}';
    }
}
