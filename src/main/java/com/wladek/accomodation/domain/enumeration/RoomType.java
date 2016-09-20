package com.wladek.accomodation.domain.enumeration;

/**
 * Created by wladek on 9/20/16.
 */
public enum RoomType {
    DOUBLE("Double"),
    SINGLE("Single");

    String name;

    RoomType(String single) {
        this.name = single;
    }
}
