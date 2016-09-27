package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.enumeration.ItemName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by wladek on 9/28/16.
 */
@Entity
public class RoomItemCost extends AbstractModel{
    @Enumerated(EnumType.STRING)
    private ItemName itemName;
    @NotNull(message = "Item cost must be provided")
    private Long unitCost = new Long(50);

    public ItemName getItemName() {
        return itemName;
    }

    public void setItemName(ItemName itemName) {
        this.itemName = itemName;
    }

    public Long getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Long unitCost) {
        this.unitCost = unitCost;
    }
}
