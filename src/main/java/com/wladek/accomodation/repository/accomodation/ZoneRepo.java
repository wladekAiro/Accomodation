package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.accomodation.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 9/22/16.
 */
@Repository
public interface ZoneRepo extends JpaRepository<Zone , Long> {
}
