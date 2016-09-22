package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by wladek on 9/22/16.
 */
@Entity
public class StudentProfile {
    private String studentRegNo;
    private String faculty;
    private String department;
    private String course;
    @OneToOne
    private User student;

    public String getStudentRegNo() {
        return studentRegNo;
    }

    public void setStudentRegNo(String studentRegNo) {
        this.studentRegNo = studentRegNo;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
