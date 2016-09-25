package com.wladek.accomodation.service.accomodation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wladek on 1/1/10.
 */
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    RoomService roomService;

    @Override
    public String bookRoom(Long bedId) {
        return null;
    }
}
