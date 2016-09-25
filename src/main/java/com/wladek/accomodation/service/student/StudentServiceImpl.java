package com.wladek.accomodation.service.student;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.StudentProfile;
import com.wladek.accomodation.repository.accomodation.ProfileRepo;
import com.wladek.accomodation.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by wladek on 9/25/16.
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    ProfileRepo profileRepo;

    @Override
    public StudentProfile loadProfile() {
        User student = getCurrentStudent();

        StudentProfile profile = student.getProfile();

        if (profile == null){
            return null;
        }else{
            return profile;
        }
    }

    @Override
    public User getCurrentStudent() {
        return getCurrentUser();
    }

    @Override
    public StudentProfile createProfile(StudentProfile studentProfile) {
        User student = getCurrentStudent();

        StudentProfile profile = student.getProfile();

        if (profile == null){

            profile = studentProfile;
            profile.setStudent(student);

        }else {

            profile.setGender(studentProfile.getGender());
            profile.setCourse(studentProfile.getCourse());
            profile.setDepartment(studentProfile.getDepartment());
            profile.setStudentRegNo(studentProfile.getStudentRegNo());
            profile.setStudent(student);
        }

        return profileRepo.save(profile);
    }


    public User getCurrentUser(){

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getUser();
    }
}
