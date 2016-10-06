package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Hostel;
import com.wladek.accomodation.repository.accomodation.HostelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Service
public class HostelServiceImpl implements HostelService {
    @Autowired
    HostelRepo hostelRepo;
    @Autowired
    ZoneService zoneService;
    @Override
    public Hostel create(Hostel hostel) {

        if (hostel.getZoneId() != null){
            hostel.setZone(zoneService.findById(hostel.getZoneId()));
        }

        return hostelRepo.save(hostel);
    }

    @Override
    public Hostel findById(Long id) {
        return hostelRepo.findOne(id);
    }

    @Override
    public List<Hostel> findAll() {
        return hostelRepo.findAll();
    }

    @Override
    public Hostel update(Hostel hostel) {
        Hostel hostelInDb = findById(hostel.getId());
        hostelInDb.setName(hostel.getName());
        hostelInDb.setCode(hostel.getCode());
        return hostelRepo.save(hostelInDb);
    }

    @Override
    public void delete(Hostel hostel) {
        hostelRepo.delete(hostel.getId());
    }
}
