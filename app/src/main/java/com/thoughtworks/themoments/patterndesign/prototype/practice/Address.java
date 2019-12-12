package com.thoughtworks.themoments.patterndesign.prototype.practice;

import androidx.annotation.NonNull;

/**
 * Created by Zhu on 2019-12-12
 */
public class Address implements Cloneable {
    public String city;
    public String district;
    public String street;

    public Address(String city, String district, String street) {
        this.city = city;
        this.district = district;
        this.street = street;
    }

    @NonNull
    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
