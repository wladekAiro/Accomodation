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
}
