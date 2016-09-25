package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.accomodation.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 9/26/16.
 */
@Repository
public interface ProfileRepo extends JpaRepository<StudentProfile , Long> {
}
