package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.enumeration.Gender;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wladek on 9/20/16.
 */
@Entity
public class Block extends AbstractModel {
    @NotEmpty(message = "Provide block name")
    private String name;
    @NotEmpty(message = "Provide block code")
    private String code;
    private Gender gender;
    @ManyToOne
    private Hostel hostel;
    @OneToMany(mappedBy = "block" , fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Room> rooms = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Transient
    private Long hostelId;

    public Long getHostelId() {
        return hostelId;
    }

    public void setHostelId(Long hostelId) {
        this.hostelId = hostelId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
