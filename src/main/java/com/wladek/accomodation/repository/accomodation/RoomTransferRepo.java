package com.wladek.accomodation.repository.accomodation;

import com.wladek.accomodation.domain.accomodation.RoomTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wladek on 10/7/16.
 */
@Repository
public interface RoomTransferRepo extends JpaRepository<RoomTransfer , Long> {
}
