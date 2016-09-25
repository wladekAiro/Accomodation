package com.wladek.accomodation.domain.enumeration;

/**
 * Created by wladek on 9/20/16.
 */
public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double"),
    TRIPLE("Triple"),
    QUADRUPLE("Quadruple");

    String name;

    RoomType(String single) {
        this.name = single;
    }
}
