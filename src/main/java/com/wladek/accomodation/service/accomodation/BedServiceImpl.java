package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.Bed;
import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.domain.accomodation.RoomItem;
import com.wladek.accomodation.domain.enumeration.BedStatus;
import com.wladek.accomodation.domain.enumeration.ItemName;
import com.wladek.accomodation.domain.enumeration.RoomItemClearStatus;
import com.wladek.accomodation.repository.accomodation.BedRepo;
import com.wladek.accomodation.repository.accomodation.ItemCostRepo;
import com.wladek.accomodation.repository.accomodation.RoomItemRepo;
import com.wladek.accomodation.service.UserDetailsImpl;
import com.wladek.accomodation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Service
@Transactional
public class BedServiceImpl implements BedService {
    @Autowired
    BedRepo bedRepo;
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;
    @Autowired
    ItemCostRepo itemCostRepo;
    @Autowired
    RoomItemRepo roomItemRepo;


    @Override
    public Bed create(Bed bed) {
        Room room = roomService.findById(bed.getRoomId());
        String zoneCode = room.getBlock().getHostel().getZone().getCode();
        String hostelCode = room.getBlock().getHostel().getCode();
        String blockCode = room.getBlock().getCode();
        String roomCode = room.getName();
        String bedNo = zoneCode + "-" + hostelCode + "-" + blockCode + "-" + roomCode + "-" + bed.getNumber();
        bed.setNumber(bedNo);
        bed.setRoom(room);
        bed.setStatus(BedStatus.AVAILABLE);
        return bedRepo.save(bed);
    }

    @Override
    public Bed findOne(Long id) {
        return bedRepo.findOne(id);
    }

    @Override
    public List<Bed> findAll() {
        return bedRepo.findAll();
    }

    @Override
    public Bed update(Bed bed) {
        Bed bedInDb = findOne(bed.getId());
        bedInDb.setBedType(bed.getBedType());
        bedInDb.setNumber(bed.getNumber());

        if (bed.getStatus() != null) {
            bedInDb.setStatus(bed.getStatus());
        }

        return bedRepo.save(bedInDb);
    }

    @Override
    public void delete(Bed bed) {

    }

    @Override
    public String bookBed(Bed bed) {
        String result = null;

        User student = getCurrentUser();

        Bed studentBed = null;

        if (student.getBed() != null) {
            studentBed = student.getBed();
            if (studentBed.getStatus() == BedStatus.BOOKED) {
                return "You have already booked for a bed , cancel your previous booking to continue";
            } else if (studentBed.getStatus() == BedStatus.OCCUPIED) {
                return "You have already occupied a bed";
            }
        } else {
            if (bed.getStatus() == BedStatus.BOOKED) {
                return "This bed has been booked";
            } else if (bed.getStatus() == BedStatus.OCCUPIED) {
                return "This bead is already occupied";
            } else {

                bed.setStatus(BedStatus.BOOKED);
                bed.setStudent(student);

                assignItems(studentBed.getRoom() , student);

                bedRepo.save(bed);

                result = "Booking was successful. ";
            }
        }

        return result;
    }

    private void assignItems(Room room , User student) {
        List<Bed> bookedBeds = new ArrayList<>();
        List<Bed> allBeds = room.getBeds();

        for (Bed bed : allBeds){
            if (bed.getStatus() == BedStatus.BOOKED || bed.getStatus() == BedStatus.RESERVED){
                bookedBeds.add(bed);
            }
        }

        //check if there are no bookings and assign additional room items
        if (bookedBeds.isEmpty()){
            //First booking assign additional items
            List<RoomItem> roomItems = new ArrayList<>();

            RoomItem roomItem = new RoomItem();
            roomItem.setItemName(ItemName.BROOM);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.BROOM).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItem = new RoomItem();
            roomItem.setItemName(ItemName.DUSTBIN);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.DUSTBIN).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItem = new RoomItem();
            roomItem.setItemName(ItemName.CURTAIN);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.CURTAIN).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItem = new RoomItem();
            roomItem.setItemName(ItemName.MATRES);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.MATRES).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItemRepo.save(roomItems);
        }else {
            //Assign single item
            RoomItem roomItem = new RoomItem();
            roomItem.setItemName(ItemName.MATRES);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.MATRES).getUnitCost());
            roomItem.setStudent(student);

            roomItemRepo.save(roomItem);
        }
    }

    @Override
    public Bed getStudentBed() {

        User student = getCurrentUser();

        if (student.getBed() != null) {
            return student.getBed();
        }

        return null;
    }

    @Override
    public List<RoomItem> getStudentRoomItems(boolean getAll) {
        User student = getCurrentUser();

        List<RoomItem> roomItems = new ArrayList<>();

        if (!student.getRoomItems().isEmpty()) {

            if (getAll) {
                roomItems.addAll(student.getRoomItems());
            } else {
                for (RoomItem item : student.getRoomItems()) {
                    if (item.getClearStatus() == RoomItemClearStatus.ISSUED) {
                        roomItems.add(item);
                    }
                }
            }
        }

        return roomItems;
    }

    public User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

        return userService.findById(userDetails.getUser().getId());
    }

}
