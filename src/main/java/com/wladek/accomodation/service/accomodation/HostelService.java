package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Hostel;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
public interface HostelService {
    public Hostel create(Hostel hostel);
    public Hostel findById(Long id);
    public List<Hostel> findAll();
    public Hostel update(Hostel hostel);
    public void delete(Hostel hostel);
}
