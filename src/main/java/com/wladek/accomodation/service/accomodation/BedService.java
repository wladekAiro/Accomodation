package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Bed;
import com.wladek.accomodation.domain.accomodation.RoomItem;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
public interface BedService {
    public Bed create(Bed bed);
    public Bed findOne(Long bed);
    public List<Bed> findAll();
    public Bed update(Bed bed);
    public void delete(Bed bed);
    public String bookBed(Bed bed);
    public Bed getStudentBed(Long studentId);
    public List<RoomItem> getStudentRoomItems(boolean getAll);
    public void cancelBooking(Long studentId);
    public String clearBed(Long bedId);
}
