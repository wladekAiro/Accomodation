package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.accomodation.RoomItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 9/28/16.
 */
@Repository
public interface RoomItemRepo extends JpaRepository<RoomItem , Long>{
}
