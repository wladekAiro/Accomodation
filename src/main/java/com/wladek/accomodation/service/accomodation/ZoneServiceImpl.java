package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Zone;
import com.wladek.accomodation.repository.accomodation.ZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Service
public class ZoneServiceImpl implements ZoneService{
    @Autowired
    ZoneRepo zoneRepo;

    @Override
    public Zone create(Zone zone) {
        return zoneRepo.save(zone);
    }

    @Override
    public Zone findById(Long id) {
        return zoneRepo.findOne(id);
    }

    @Override
    public List<Zone> findAll() {
        return zoneRepo.findAll();
    }

    @Override
    public Zone update(Zone zone) {
        Zone zoneInDb = findById(zone.getId());
        zoneInDb.setName(zone.getName());
        zoneInDb.setCode(zone.getCode());
        return zoneRepo.save(zoneInDb);
    }

    @Override
    public void delete(Zone zone) {
        zoneRepo.delete(zone.getId());
    }
}
