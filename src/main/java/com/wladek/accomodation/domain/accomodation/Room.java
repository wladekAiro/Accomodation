package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.enumeration.BedStatus;
import com.wladek.accomodation.domain.enumeration.RoomType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wladek on 9/20/16.
 */
@Entity
public class Room extends AbstractModel {
    @NotEmpty(message = "Provide room number")
    private String name;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @ManyToOne
    private Block block;
    @NotNull(message = "Provide cost of room")
    private Long cost;
    private int capacity;
    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Bed> beds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    @Transient
    private Long blockId;

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String status(List<Bed> bedList , int roomCapacity){
        String status = "";
        int spaceAvailable = 0;

        for (Bed bed : bedList){
            if (bed.getStatus() == BedStatus.AVAILABLE){
                spaceAvailable = spaceAvailable + 1;
            }
        }

        if (spaceAvailable == 0){
            status = "Full";
        }else {
            status = spaceAvailable+" beds available";
        }

        return status;
    }

    public boolean capacityExceeded(){
        return (capacity <= beds.size());
    }
}
