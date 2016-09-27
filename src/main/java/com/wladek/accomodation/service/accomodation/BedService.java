package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Bed;

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
}
