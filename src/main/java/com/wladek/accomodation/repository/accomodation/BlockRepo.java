package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.accomodation.Block;
import com.wladek.accomodation.domain.accomodation.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 9/22/16.
 */
@Repository
public interface BlockRepo extends JpaRepository<Block , Long> {
}
