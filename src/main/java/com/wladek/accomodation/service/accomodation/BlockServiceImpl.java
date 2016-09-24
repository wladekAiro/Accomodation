package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Block;
import com.wladek.accomodation.repository.accomodation.BlockRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
        blockInDb.setHostel(block.getHostel());
        return blockRepo.save(blockInDb);
    }

    @Override
    public void delete(Block block) {
        blockRepo.delete(block.getId());
    }
}
