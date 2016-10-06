package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.repository.accomodation.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by wladek on 9/22/16.
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    BlockService blockService;

    @Override
    public Room create(Room room) {
        room.setBlock(blockService.findById(room.getBlockId()));
        return roomRepo.save(room);
    }

    @Override
    public Room findById(Long id) {
        return roomRepo.findOne(id);
    }

    @Override
    public Page<Room> findByBlock(Long blockId, int page, int size) {
        return null;
    }

    @Override
    public Room update(Room room) {
        Room roomInDb = findById(room.getId());
        roomInDb.setName(room.getName());
        roomInDb.setRoomType(room.getRoomType());
        roomInDb.setCapacity(room.getCapacity());
        roomInDb.setCost(room.getCost());
        return roomRepo.save(roomInDb);
    }

    @Override
    public void delete(Room room) {
        roomRepo.delete(room.getId());
    }
}
