package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.accomodation.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 9/22/16.
 */
@Repository
public interface BedRepo extends JpaRepository<Bed , Long> {
}