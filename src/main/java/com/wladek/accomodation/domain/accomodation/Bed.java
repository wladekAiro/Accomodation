package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.enumeration.BedStatus;
import com.wladek.accomodation.domain.enumeration.BedType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by wladek on 9/20/16.
 */
@Entity
public class Bed extends AbstractModel {
    @NotEmpty(message = "Provide bed number")
    private String number;
    @Enumerated(EnumType.STRING)
    private BedType bedType;
    @Enumerated(EnumType.STRING)
    private BedStatus status;
    @ManyToOne
    private Room room;
    @OneToOne
    private User student;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public BedStatus getStatus() {
        return status;
    }

    public void setStatus(BedStatus status) {
        this.status = status;
    }

    @Transient
    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public boolean myBed(Long userId){
        return (userId == student.getId());
    }
}
