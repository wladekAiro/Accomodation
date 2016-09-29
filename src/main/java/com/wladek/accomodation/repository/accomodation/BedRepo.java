package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.accomodation.Bed;
import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.domain.enumeration.BedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Repository
public interface BedRepo extends JpaRepository<Bed , Long> {
    public List<Bed> findByStatusAndRoom(BedStatus status , Room room);
}