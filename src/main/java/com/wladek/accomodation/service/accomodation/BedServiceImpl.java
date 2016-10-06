package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.Semester;
import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.Bed;
import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.domain.accomodation.RoomItem;
import com.wladek.accomodation.domain.enumeration.BedStatus;
import com.wladek.accomodation.domain.enumeration.ItemCondition;
import com.wladek.accomodation.domain.enumeration.ItemName;
import com.wladek.accomodation.domain.enumeration.RoomItemClearStatus;
import com.wladek.accomodation.repository.accomodation.BedRepo;
import com.wladek.accomodation.repository.accomodation.ItemCostRepo;
import com.wladek.accomodation.repository.accomodation.RoomItemRepo;
import com.wladek.accomodation.repository.accomodation.SemesterRepo;
import com.wladek.accomodation.service.UserDetailsImpl;
import com.wladek.accomodation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wladek on 9/22/16.
 */
@Service
@Transactional
public class BedServiceImpl implements BedService {

    Logger logger = LoggerFactory.getLogger(BedServiceImpl.class);

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
    @Autowired
    SemesterRepo semesterRepo;

    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


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

                assignItems(bed.getRoom(), student);

                bedRepo.save(bed);

                result = "Booking was successful. ";
            }
        }

        return result;
    }

    private void assignItems(Room room, User student) {
        List<Bed> bookedBeds = new ArrayList<>();

        bookedBeds.clear();

        bookedBeds.addAll(bedRepo.findByStatusAndRoom(BedStatus.BOOKED , room));
        bookedBeds.addAll(bedRepo.findByStatusAndRoom(BedStatus.RESERVED , room));

        //check if there are no bookings and assign additional room items

        logger.info(" ++++++++++++++++++++++ BOOKED BED SIZE ++++++++++++ "+bookedBeds.size());

        if (bookedBeds.size() <= 1) {
            //First booking on this room. Assign additional items
            List<RoomItem> roomItems = new ArrayList<>();

            RoomItem roomItem = new RoomItem();
            roomItem.setItemName(ItemName.BROOM);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setItemCondition(ItemCondition.GOOD);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.BROOM).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItem = new RoomItem();
            roomItem.setItemName(ItemName.DUSTBIN);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setItemCondition(ItemCondition.GOOD);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.DUSTBIN).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItem = new RoomItem();
            roomItem.setItemName(ItemName.CURTAIN);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setItemCondition(ItemCondition.GOOD);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.CURTAIN).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItem = new RoomItem();
            roomItem.setItemName(ItemName.MATRES);
            roomItem.setItemCondition(ItemCondition.GOOD);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.MATRES).getUnitCost());
            roomItem.setStudent(student);
            roomItems.add(roomItem);

            roomItemRepo.save(roomItems);

        } else {
            //Assign single item
            RoomItem roomItem = new RoomItem();
            roomItem.setItemName(ItemName.MATRES);
            roomItem.setClearStatus(RoomItemClearStatus.ASSIGNED);
            roomItem.setItemCondition(ItemCondition.GOOD);
            roomItem.setCost(itemCostRepo.findByItemName(ItemName.MATRES).getUnitCost());
            roomItem.setStudent(student);

            roomItemRepo.save(roomItem);

        }
    }

    @Override
    public Bed getStudentBed(Long studentId) {

        User student = userService.findById(studentId);

        if (student.getBed() != null) {
            switch (student.getBed().getStatus()) {
                case BOOKED:
                    return student.getBed();
                case RESERVED:
                    return student.getBed();
                case OCCUPIED:
                    return student.getBed();
                default:
                    return null;
            }
        }

        return null;
    }

    @Override
    public List<RoomItem> getStudentRoomItems(boolean getAll) {
        User student = userService.findById(getCurrentUser().getId());


        if (getAll) {
            return roomItemRepo.findByStudent(student);
        } else {
            List<RoomItem> roomItems = new ArrayList<>();

            roomItems.clear();
            roomItems.addAll(roomItemRepo.findByClearStatusAndStudent(RoomItemClearStatus.ASSIGNED, student));
            roomItems.addAll(roomItemRepo.findByClearStatusAndStudent(RoomItemClearStatus.ISSUED, student));

            return roomItems;
        }
    }

    @Override
    public void cancelBooking(Long studentId) {
        //Update room booking status
        Bed bed = getStudentBed(studentId);
        bed.setStudent(null);
        bed.setStatus(BedStatus.AVAILABLE);
        update(bed);

        //un-assign room items
        List<RoomItem> roomItems = roomItemRepo.findByClearStatusAndStudent(RoomItemClearStatus.ASSIGNED,
                userService.findById(studentId));
        roomItemRepo.delete(roomItems);
    }

    @Override
    public String clearBed(Long bedId) {
        Bed bedInDb = findOne(bedId);

        List<Semester> semesters = semesterRepo.findAll();

        Date currentDate = new Date();
        Long twoWeeks = new Long(14*24*60*60*1000);

        Semester currentSemester = semesters.get(0);

        int weeks  = getWeeksBetween(currentDate , currentSemester.getSemesterEndDate());

        logger.info(" ++++++++++++++ WEEK DIFFERENCE ++++++++++++++ " +weeks);


        if (weeks <= 2){
            //accept clearance

            switch (currentSemester.getSemCount()) {
                case FIRST:
                    bedInDb.setStatus(BedStatus.RESERVED);
                    break;
                case SECOND:
                    bedInDb.setStatus(BedStatus.AVAILABLE);
                    bedInDb.setStudent(null);
                    break;
                case THIRD:
                    bedInDb.setStatus(BedStatus.AVAILABLE);
                    bedInDb.setStudent(null);
                    break;
                default:
                    bedInDb.setStatus(BedStatus.AVAILABLE);
                    bedInDb.setStudent(null);
                    break;
            }

            bedRepo.save(bedInDb);


            return "Cleared";
        }else {
            return "You cant clear now, wait until two  weeks to end of semester to clear";
        }

    }

    public User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

        return userService.findById(userDetails.getUser().getId());
    }

    public static int getWeeksBetween (Date a, Date b) {

        if (b.before(a)) {
            return -getWeeksBetween(b, a);
        }
        a = resetTime(a);
        b = resetTime(b);

        Calendar cal = new GregorianCalendar();
        cal.setTime(a);
        int weeks = 0;
        while (cal.getTime().before(b)) {
            // add another week
            cal.add(Calendar.WEEK_OF_YEAR, 1);
            weeks++;
        }
        return weeks;
    }

    public static Date resetTime (Date d) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
