package com.wladek.accomodation.domain.accomodation;

import com.wladek.accomodation.domain.AbstractModel;
import com.wladek.accomodation.domain.enumeration.ItemName;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by wladek on 9/28/16.
 */
@Entity
public class RoomItemCost extends AbstractModel{

    private static final String NUMBER_FORMAT = "^[0-9]+";

    @Enumerated(EnumType.STRING)
    private ItemName itemName;
    @NotNull(message = "Item cost must be provided")
    @NumberFormat
    private Long unitCost = new Long(50);
    @NotNull(message = "Item cost must be provided")
    @NumberFormat
    private Long totalAvailable = new Long(0);
    private Long totalIssued = new Long(0);

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

    public Long getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(Long totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public Long getTotalIssued() {
        return totalIssued;
    }

    public void setTotalIssued(Long totalIssued) {
        this.totalIssued = totalIssued;
    }

    public Long getAvailable(Long total, Long issued){

        return (total - issued);
    }
}
