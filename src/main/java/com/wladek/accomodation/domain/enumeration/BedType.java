package com.wladek.accomodation.domain.enumeration;

/**
 * Created by wladek on 9/20/16.
 */
public enum  BedType {
    UPPER("Upper"),
    LOWER("Lower");

    String name;

    BedType(String string) {
        this.name = string;
    }
}
