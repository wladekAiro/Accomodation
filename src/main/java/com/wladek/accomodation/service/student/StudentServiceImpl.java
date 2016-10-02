package com.wladek.accomodation.service.student;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.RoomItem;
import com.wladek.accomodation.domain.accomodation.StudentProfile;
import com.wladek.accomodation.domain.enumeration.RoomItemClearStatus;
import com.wladek.accomodation.repository.accomodation.ItemCostRepo;
import com.wladek.accomodation.repository.accomodation.ProfileRepo;
import com.wladek.accomodation.repository.accomodation.RoomItemRepo;
import com.wladek.accomodation.service.UserDetailsImpl;
import com.wladek.accomodation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wladek on 9/25/16.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService{
    @Autowired
    ProfileRepo profileRepo;
    @Autowired
    UserService userService;
    @Autowired
    RoomItemRepo roomItemRepo;
    @Autowired
    ItemCostRepo itemCostRepo;

    @Override
    public StudentProfile loadProfile(Long id) {
        User student = getCurrentStudent(id);

        StudentProfile profile = student.getProfile();

        if (profile == null){
            return null;
        }else{
            return profile;
        }
    }

    @Override
    public User getCurrentStudent(Long id) {
        return userService.findById(id);
    }

    @Override
    public StudentProfile createProfile(StudentProfile studentProfile) {
        User student = getCurrentUser();

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

    @Override
    public Page<StudentProfile> getAll(int page, int size) {
        page = page -1;
        PageRequest pageRequest = new PageRequest(page , size);
        return profileRepo.findAll(pageRequest);
    }

    @Override
    public Page<StudentProfile> getByStudentNumber(String studentNumber, int page, int size) {
        page = page -1;
        PageRequest pageRequest = new PageRequest(page ,size);
        return profileRepo.findByStudentRegNo(studentNumber , pageRequest);
    }

    @Override
    public StudentProfile loadProfileById(Long profileId) {
        return profileRepo.findOne(profileId);
    }

    @Override
    public List<RoomItem> getStudentRoomItems(Long userId, boolean getAll) {
        User student = userService.findById(userId);


        if (getAll) {
            return roomItemRepo.findByStudent(student);
        } else {
            List<RoomItem> roomItems = new ArrayList<>();

            roomItems.clear();
            roomItems.addAll(roomItemRepo.findByClearStatusAndStudent(RoomItemClearStatus.ASSIGNED, student));
            roomItems.addAll(roomItemRepo.findByClearStatusAndStudent(RoomItemClearStatus.ISSUED, student));

            return roomItems;
        }
    }

    @Override
    public String issueItem(Long itemId) {
        return null;
    }


    public User getCurrentUser(){

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getUser();
    }
}
