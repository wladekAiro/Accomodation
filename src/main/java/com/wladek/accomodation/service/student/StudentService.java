package com.wladek.accomodation.service.student;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.StudentProfile;

/**
 * Created by wladek on 9/25/16.
 */
public interface StudentService {
    public StudentProfile loadProfile(Long id);
    public User getCurrentStudent(Long id);
    public StudentProfile createProfile(StudentProfile studentProfile);
}
