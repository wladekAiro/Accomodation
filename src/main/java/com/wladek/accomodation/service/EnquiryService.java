package com.wladek.accomodation.service;

import com.wladek.accomodation.domain.pension.Enquiry;

/**
 * Created by wladek on 1/14/16.
 */
public interface EnquiryService {
    public Enquiry make(Enquiry enquiry);
    public Enquiry findOne(Long id);
}
