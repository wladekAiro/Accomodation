package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.enumeration.RoomType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by wladek on 9/20/16.
 */
public class Room extends AbstractModel {
    private String name;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
