package com.wladek.accomodation.repository;

import com.wladek.accomodation.domain.accomodation.NonResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 10/7/16.
 */
@Repository
public interface NonResidentRepo extends JpaRepository<NonResident , Long> {
}
