package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Zone;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
public interface ZoneService {
    public Zone create(Zone zone);
    public Zone findById(Long id);
    public List<Zone> findAll();
    public Zone update(Zone zone);
    public void delete(Zone zone);
}
