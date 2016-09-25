package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Bed;
import com.wladek.accomodation.domain.enumeration.BedStatus;
import com.wladek.accomodation.repository.accomodation.BedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Service
public class BedServiceImpl implements BedService{
    @Autowired
    BedRepo bedRepo;
    @Autowired
    RoomService roomService;


    @Override
    public Bed create(Bed bed) {
        bed.setRoom(roomService.findById(bed.getRoomId()));
        bed.setStatus(BedStatus.AVAILABLE);
        return bedRepo.save(bed);
    }

    @Override
    public Bed findOne(Long id) {
        return bedRepo.findOne(id);
    }

    @Override
    public List<Bed> findAll() {
        return bedRepo.findAll();
    }

    @Override
    public Bed update(Bed bed) {
        Bed bedInDb = findOne(bed.getId());
        bedInDb.setBedType(bed.getBedType());
        bedInDb.setNumber(bed.getNumber());
        bedInDb.setStatus(bed.getStatus());
        return bedRepo.save(bed);
    }

    @Override
    public void delete(Bed bed) {

    }
}
