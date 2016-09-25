package com.wladek.accomodation.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by wladek on 1/1/10.
 */
@Entity
public class Semester extends AbstractModel {
    private Date semesterStartDate;
    private Date semesterEndDate;
    private boolean booking;
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
}
