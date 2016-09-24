package com.wladek.accomodation.web.admin;

import com.wladek.accomodation.domain.accomodation.Block;
import com.wladek.accomodation.domain.accomodation.Hostel;
import com.wladek.accomodation.domain.accomodation.Room;
import com.wladek.accomodation.domain.accomodation.Zone;
import com.wladek.accomodation.service.accomodation.BlockService;
import com.wladek.accomodation.service.accomodation.HostelService;
import com.wladek.accomodation.service.accomodation.RoomService;
import com.wladek.accomodation.service.accomodation.ZoneService;
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
 * Created by wladek on 9/22/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    ZoneService zoneService;
    @Autowired
    HostelService hostelService;
    @Autowired
    BlockService blockService;
    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public String home(Model model){

        return "/admin/index";
    }

    @RequestMapping(value = "/zones" , method = RequestMethod.GET)
    public String zones(Model model){
        List<Zone> zoneList = zoneService.findAll();

        model.addAttribute("zoneList" , zoneList);
        model.addAttribute("zoneList" , zoneList);
        model.addAttribute("zone" , new Zone());

        return "/admin/zone/index";
    }

    @RequestMapping(value = "/zone/createzone" , method = RequestMethod.POST)
    public String createZone(@ModelAttribute @Valid Zone zone , BindingResult result , RedirectAttributes redirectAttributes ,
                             Model model){

        if (result.hasErrors()){
            List<Zone> zoneList = zoneService.findAll();

            model.addAttribute("zoneList" , zoneList);
            model.addAttribute("zone" , zone);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/zone/index";
        }
        Zone newZone = zoneService.create(zone);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", newZone.getName() + " zone created");

        return "redirect:/admin/zones";
    }

    @RequestMapping(value = "/zones/view/{zoneId}" , method = RequestMethod.GET)
    public String view(@PathVariable("zoneId") Long zoneId  , Model model){
        Zone zone = zoneService.findById(zoneId);
        model.addAttribute("hostel" , new Hostel());
        model.addAttribute("zone" , zone);

        return "/admin/zone/view";
    }

    @RequestMapping(value = "/zone/createhostel" , method = RequestMethod.POST)
    public String createHostel(@ModelAttribute @Valid Hostel hostel , BindingResult result , RedirectAttributes redirectAttributes ,
                             Model model){

        if (result.hasErrors()){

            Zone zone = zoneService.findById(hostel.getZoneId());

            model.addAttribute("hostel" , hostel);
            model.addAttribute("zone" , zone);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/zone/view";
        }

        Hostel newHostel = hostelService.create(hostel);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", newHostel.getName() + " hostel created");

        return "redirect:/admin/zones/view/"+hostel.getZoneId();
    }

    @RequestMapping(value = "/hostel/view/{hostelId}" , method = RequestMethod.GET)
    public String viewHostel(@PathVariable("hostelId") Long hostelId  , Model model){
        Hostel hostel = hostelService.findById(hostelId);
        model.addAttribute("block" , new Block());
        model.addAttribute("hostel" , hostel);

        return "/admin/hostel/view";
    }

    @RequestMapping(value = "/block/createblock" , method = RequestMethod.POST)
    public String createBlock(@ModelAttribute @Valid Block block , BindingResult result , RedirectAttributes redirectAttributes ,
                             Model model){

        if (result.hasErrors()){
            Hostel hostel = hostelService.findById(block.getHostelId());
            model.addAttribute("block" , block);
            model.addAttribute("hostel" , hostel);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/hostel/view";

        }

        Block newBlock = blockService.create(block);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", newBlock.getName() + " block created");

        return "redirect:/admin/hostel/view/"+block.getHostelId();
    }

    @RequestMapping(value = "/block/view/{blockId}" , method = RequestMethod.GET)
    public String viewBlock(@PathVariable("blockId") Long blockId  ,
                            @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                            @RequestParam(value = "size" , required = false , defaultValue = "5") int size, Model model){
        Block block = blockService.findById(blockId);
        Page<Room> roomsPage = blockService.findRooms(blockId , page ,size);
        model.addAttribute("block" , block);
        model.addAttribute("roomsPage" , roomsPage);
        model.addAttribute("room" , new Room());
        model.addAttribute("pagenatedUrl" , "/admin/block/view/"+blockId);


        return "/admin/block/view";
    }

    @RequestMapping(value = "/room/createroom" , method = RequestMethod.POST)
    public String createRoom(@ModelAttribute @Valid Room room , BindingResult result ,
                             @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                             @RequestParam(value = "size" , required = false , defaultValue = "15") int size,RedirectAttributes redirectAttributes ,
                              Model model){

        if (result.hasErrors()){
            Block block = blockService.findById(room.getBlockId());
            Page<Room> roomsPage = blockService.findRooms(room.getBlockId() , page ,size);
            model.addAttribute("block" , block);
            model.addAttribute("roomsPage" , roomsPage);
            model.addAttribute("room" , room);
            model.addAttribute("pagenatedUrl" , "/admin/block/view/"+room.getBlockId());;

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/block/view";

        }

        Room newRoom = roomService.create(room);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", "Room "+newRoom.getName() + " created");

        return "redirect:/admin/block/view/"+room.getBlockId()+"?page="+page+"&size="+size;
    }
}
