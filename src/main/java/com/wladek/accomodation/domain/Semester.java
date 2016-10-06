package com.wladek.accomodation.domain;

import com.wladek.accomodation.domain.accomodation.SemCount;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by wladek on 1/1/10.
 */
@Entity
public class Semester extends AbstractModel {
    private Date semesterStartDate;
    private Date semesterEndDate;
    private SemCount semCount;
    private Boolean booking = true;
    private Date offSessionBookingStartDate;

    public Date getSemesterEndDate() {
        return semesterEndDate;
    }

    public void setSemesterEndDate(Date semesterEndDate) {
        this.semesterEndDate = semesterEndDate;
    }

    public Date getSemesterStartDate() {
        return semesterStartDate;
    }

    public void setSemesterStartDate(Date semesterStartDate) {
        this.semesterStartDate = semesterStartDate;
    }

    public boolean isBooking() {
        return booking;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    public Date getOffSessionBookingStartDate() {
        return offSessionBookingStartDate;
    }

    public void setOffSessionBookingStartDate(Date offSessionBookingStartDate) {
        this.offSessionBookingStartDate = offSessionBookingStartDate;
    }

    @Transient
    private String semStartDate;

    @Transient
    private String semEndDate;

    @Transient
    private String offSessionDate;

    public String getSemStartDate() {
        return semStartDate;
    }

    public void setSemStartDate(String semStartDate) {
        this.semStartDate = semStartDate;
    }

    public String getSemEndDate() {
        return semEndDate;
    }

    public void setSemEndDate(String semEndDate) {
        this.semEndDate = semEndDate;
    }

    public String getOffSessionDate() {
        return offSessionDate;
    }

    public void setOffSessionDate(String offSessionDate) {
        this.offSessionDate = offSessionDate;
    }

    public SemCount getSemCount() {
        return semCount;
    }

    public void setSemCount(SemCount semCount) {
        this.semCount = semCount;
    }
}
