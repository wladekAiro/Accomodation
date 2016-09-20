package com.wladek.accomodation.repository;

import com.wladek.accomodation.domain.pension.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 1/13/16.
 */
@Repository
public interface EmployerRepo extends JpaRepository<Employer , Long> {
}
