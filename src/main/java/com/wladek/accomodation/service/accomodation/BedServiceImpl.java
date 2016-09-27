package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.accomodation.Bed;
import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.domain.enumeration.BedStatus;
import com.wladek.accomodation.repository.accomodation.BedRepo;
import com.wladek.accomodation.service.UserDetailsImpl;
import com.wladek.accomodation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Service
@Transactional
public class BedServiceImpl implements BedService{
    @Autowired
    BedRepo bedRepo;
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;


    @Override
    public Bed create(Bed bed) {
        Room room = roomService.findById(bed.getRoomId());
        String zoneCode = room.getBlock().getHostel().getZone().getCode();
        String hostelCode = room.getBlock().getHostel().getCode();
        String blockCode = room.getBlock().getCode();
        String roomCode = room.getName();
        String bedNo = zoneCode+"-"+hostelCode+"-"+blockCode+"-"+roomCode+"-"+bed.getNumber();
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
        bedInDb.setStatus(bed.getStatus());
        return bedRepo.save(bed);
    }

    @Override
    public void delete(Bed bed) {

    }

    @Override
    public String bookBed(Bed bed) {

        String result = null;

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

        User student = userService.findById(userDetails.getUser().getId());

        Bed studentBed = null;

        if (student.getBed() != null){
            studentBed = student.getBed();
            if (studentBed.getStatus() == BedStatus.BOOKED){
                return "You have already booked for a bed , cancel your previous booking to continue";
            }else if (studentBed.getStatus() == BedStatus.OCCUPIED){
                return "You have already occupied a bed";
            }
        }else {
            if (bed.getStatus() == BedStatus.BOOKED){
                return "This bed has been booked";
            }else if(bed.getStatus() == BedStatus.OCCUPIED){
                return "This bead is already occupied";
            }else {

                bed.setStatus(BedStatus.BOOKED);
                bed.setStudent(student);

                bedRepo.save(bed);

                result = "Booking was successful";
            }
        }

        return result;
    }

}
