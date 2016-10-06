package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Block;
import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.repository.accomodation.BlockRepo;
import com.wladek.accomodation.repository.accomodation.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Service
public class BlockServiceImpl implements BlockService{
    @Autowired
    BlockRepo blockRepo;
    @Autowired
    HostelService hostelService;
    @Autowired
    RoomRepo roomRepo;

    @Override
    public Block create(Block block) {
        block.setHostel(hostelService.findById(block.getHostelId()));
        return blockRepo.save(block);
    }

    @Override
    public Block findById(Long id) {
        return blockRepo.findOne(id);
    }

    @Override
    public List<Block> findAll() {
        return blockRepo.findAll();
    }

    @Override
    public Block update(Block block) {
        Block blockInDb = findById(block.getId());
        blockInDb.setCode(block.getCode());
        blockInDb.setName(block.getName());
        blockInDb.setGender(block.getGender());
        return blockRepo.save(blockInDb);
    }

    @Override
    public void delete(Block block) {
        blockRepo.delete(block.getId());
    }

    @Override
    public Page<Room> findRooms(Long blockId, int page, int size) {
        page = page - 1;
        PageRequest pageRequest = new PageRequest(page ,size);
        return roomRepo.findByBlock(findById(blockId) , pageRequest);
    }
}
