package com.wladek.accomodation.service.student;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.RoomItem;
import com.wladek.accomodation.domain.accomodation.RoomTransfer;
import com.wladek.accomodation.domain.accomodation.StudentProfile;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wladek on 9/25/16.
 */
public interface StudentService {
    public StudentProfile loadProfile(Long id);
    public User getCurrentStudent(Long id);
    public StudentProfile createProfile(StudentProfile studentProfile);
    public Page<StudentProfile> getAll(int page, int size);
    public Page<StudentProfile> getByStudentNumber(String studentNumber , int page, int size);
    public StudentProfile loadProfileById(Long profileId);
    public List<RoomItem> getStudentRoomItems(Long userId , boolean getAll);
    public String issueItem(Long itemId);
    public RoomTransfer makeRequest(RoomTransfer roomTransfer);
}
