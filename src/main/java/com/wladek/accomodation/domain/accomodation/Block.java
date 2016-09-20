package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;

import javax.persistence.Entity;

/**
 * Created by wladek on 9/20/16.
 */
@Entity
public class Block extends AbstractModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
