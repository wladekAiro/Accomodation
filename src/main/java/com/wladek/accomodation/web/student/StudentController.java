package com.wladek.accomodation.web.student;

import com.wladek.accomodation.domain.accomodation.*;
import com.wladek.accomodation.service.accomodation.BedService;
import com.wladek.accomodation.service.accomodation.BlockService;
import com.wladek.accomodation.service.accomodation.HostelService;
import com.wladek.accomodation.service.accomodation.RoomService;
import com.wladek.accomodation.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wladek on 9/25/16.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    HostelService hostelService;
    @Autowired
    BlockService blockService;
    @Autowired
    BedService bedService;
    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/profile" , method = RequestMethod.GET)
    public String loadProfile(Model model){

        StudentProfile profile = studentService.loadProfile();

        model.addAttribute("profile" , profile);

        if (profile == null){
            model.addAttribute("studentProfile" , new StudentProfile());
            model.addAttribute("url" , "/student/profile/createProfile");
        }else {
            model.addAttribute("studentProfile" , profile);
            model.addAttribute("url" , "/student/profile/updateProfile");
        }

        return "/student/profile";
    }

    @RequestMapping(value = "/profile/createProfile" , method = RequestMethod.POST)
    public String createProfile(@Valid @ModelAttribute StudentProfile studentProfile , BindingResult result , RedirectAttributes redirectAttributes ,
                                Model model){

        if (result.hasErrors()){
            model.addAttribute("studentProfile" , studentProfile);
            model.addAttribute("url" , "/student/profile/createProfile");

            model.addAttribute("message" , true);
            model.addAttribute("content" , "Form has errors");

            return "/student/profile";
        }

        StudentProfile profile = studentService.createProfile(studentProfile);

        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , "Profile created");

        return "redirect:/student/profile";
    }

    @RequestMapping(value = "/profile/updateProfile" , method = RequestMethod.POST)
    public String updateProfile(@Valid @ModelAttribute StudentProfile studentProfile , BindingResult result , RedirectAttributes redirectAttributes ,
                                Model model){

        if (result.hasErrors()){
            model.addAttribute("studentProfile" , studentProfile);
            model.addAttribute("url" , "/student/profile/updateProfile");

            model.addAttribute("message" , true);
            model.addAttribute("content" , "Form has errors");

            return "/student/profile";
        }

        StudentProfile profile = studentService.createProfile(studentProfile);

        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , "Profile updated");

        return "redirect:/student/profile";
    }

    @RequestMapping(value = "/hostels" , method = RequestMethod.GET)
    public String viewHostels(@RequestParam(value = "searchTerm" , required = false ,defaultValue = "null") String searchTerm,
                              Model model){

        List<Hostel> hostelList = hostelService.findAll();

        model.addAttribute("hostelList" , hostelList);

        return "/student/hostel/list";
    }

    @RequestMapping(value = "/hostel/{hostelId}" , method = RequestMethod.GET)
    public String viewHostel(@PathVariable("hostelId") Long hostelId  , Model model){
        Hostel hostel = hostelService.findById(hostelId);
        model.addAttribute("hostel" , hostel);

        return "/student/hostel/hostelview";
    }

    @RequestMapping(value = "/hostels/block/{blockId}" , method = RequestMethod.GET)
    public String viewBlock(@PathVariable("blockId") Long blockId  ,
                            @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                            @RequestParam(value = "size" , required = false , defaultValue = "10") int size, Model model){
        Block block = blockService.findById(blockId);
        Page<Room> roomsPage = blockService.findRooms(blockId , page ,size);
        model.addAttribute("block" , block);
        model.addAttribute("roomsPage" , roomsPage);
        model.addAttribute("pagenatedUrl" , "/student/hostel/block/"+blockId);


        return "/student/hostel/block";
    }

    @RequestMapping(value = "/room/{roomId}" , method = RequestMethod.GET)
    public String viewRoom(@PathVariable("roomId") Long roomId, Model model){

        Room room = roomService.findById(roomId);
        model.addAttribute("room" , room);
        return "/student/hostel/room";
    }

    @RequestMapping(value = "/room/bed/{bedId}" , method = RequestMethod.GET)
    public String bookBed(@PathVariable("bedId") Long bedId, @RequestParam("flag") boolean book , Model model){

        Bed bed = bedService.findOne(bedId);

        String bookBed = bedService.bookBed(bed);

        Room room = bed.getRoom();

        model.addAttribute("room" , room);
        model.addAttribute("book" , book);
        model.addAttribute("content" , bookBed);
        model.addAttribute("message", true);

        return "/student/hostel/room";
    }

    @RequestMapping(value = "/room/details" , method = RequestMethod.GET)
    public String studentRoom(@RequestParam(value = "all" , required = false , defaultValue = "false") boolean getAll , Model model){

        Bed bed = bedService.getStudentBed();

        Room room = null;

        if (bed != null){
            room = bed.getRoom();
        }

        List<RoomItem> roomItems = bedService.getStudentRoomItems(getAll);

        model.addAttribute("room" , room);
        model.addAttribute("bed" , bed);
        model.addAttribute("roomItems" , roomItems);

        return "/student/hostel/roomdetails";
    }
}
