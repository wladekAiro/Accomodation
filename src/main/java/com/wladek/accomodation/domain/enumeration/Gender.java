package com.wladek.accomodation.domain.enumeration;

/**
 * Created by wladek on 9/20/16.
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private String name;
    Gender(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
