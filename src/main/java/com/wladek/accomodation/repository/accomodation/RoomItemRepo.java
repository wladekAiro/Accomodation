package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.RoomItem;
import com.wladek.accomodation.domain.enumeration.RoomItemClearStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wladek on 9/28/16.
 */
@Repository
public interface RoomItemRepo extends JpaRepository<RoomItem , Long>{
    public List<RoomItem> findByClearStatusAndStudent(RoomItemClearStatus status , User student);
    public List<RoomItem> findByStudent(User student);
}
