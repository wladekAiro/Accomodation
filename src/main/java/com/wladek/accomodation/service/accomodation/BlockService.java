package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.accomodation.Block;

import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
public interface BlockService {
    public Block create(Block block);
    public Block findById(Long id);
    public List<Block> findAll();
    public Block update(Block block);
    public void delete(Block block);
}
