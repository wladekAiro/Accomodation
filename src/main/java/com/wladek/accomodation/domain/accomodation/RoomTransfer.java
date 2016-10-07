package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Created by wladek on 10/7/16.
 */
@Entity
public class RoomTransfer extends AbstractModel {
    @ManyToOne
    private StudentProfile profile;
    @NotEmpty(message = "You must provide your current room")
    private String currentRoom;
    @NotEmpty(message = "You must provide your preferred accommodation")
    private String prefer;
    @NotEmpty(message = "You must provide a reason for transfer request")
    @Column(columnDefinition = "TEXT")
    private String reason;

    public StudentProfile getProfile() {
        return profile;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getPrefer() {
        return prefer;
    }

    public void setPrefer(String prefer) {
        this.prefer = prefer;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Transient
    private Long profileId;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
