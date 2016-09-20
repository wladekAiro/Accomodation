package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.enumeration.BedType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by wladek on 9/20/16.
 */
public class Bed extends AbstractModel {
    private String number;
    @Enumerated(EnumType.STRING)
    private BedType bedType;
}
