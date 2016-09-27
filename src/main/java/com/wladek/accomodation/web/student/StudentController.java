package com.wladek.accomodation.web.student;

import com.wladek.accomodation.domain.accomodation.Block;
import com.wladek.accomodation.domain.accomodation.Hostel;
import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.domain.accomodation.StudentProfile;
import com.wladek.accomodation.service.accomodation.BlockService;
import com.wladek.accomodation.service.accomodation.HostelService;
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
}
