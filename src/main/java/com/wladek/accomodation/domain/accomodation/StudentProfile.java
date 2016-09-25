package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.enumeration.Gender;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by wladek on 9/22/16.
 */
@Entity
public class StudentProfile extends AbstractModel {
    @NotEmpty(message = "Provide registration number")
    private String studentRegNo;
    @NotEmpty(message = "Provide faculty")
    private String faculty;
    @NotEmpty(message = "Provide department")
    private String department;
    @NotEmpty(message = "Provide course undertaking")
    private String course;
    @NotNull(message = "Provide gender")
    private Gender gender;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
