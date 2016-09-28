package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.enumeration.Gender;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wladek on 9/20/16.
 */
@Entity
public class Hostel extends AbstractModel {
    @NotEmpty(message = "Provide name")
    private String name;
    @NotEmpty(message = "Provide code (e.g H08 )")
    private String code;
    @ManyToOne
    private Zone zone;
    @OneToMany(mappedBy = "hostel" , fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Block> blocks = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<Block> blocks) {
        this.blocks = blocks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Transient
    private Long zoneId;

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }
}
