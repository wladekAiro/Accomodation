package com.wladek.accomodation.web.admin;

import com.wladek.accomodation.domain.Semester;
import com.wladek.accomodation.domain.accomodation.*;
import com.wladek.accomodation.domain.enumeration.ItemName;
import com.wladek.accomodation.repository.accomodation.ItemCostRepo;
import com.wladek.accomodation.repository.accomodation.SemesterRepo;
import com.wladek.accomodation.service.accomodation.*;
import com.wladek.accomodation.service.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wladek on 9/22/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    ZoneService zoneService;
    @Autowired
    HostelService hostelService;
    @Autowired
    BlockService blockService;
    @Autowired
    RoomService roomService;
    @Autowired
    BedService bedService;
    @Autowired
    ItemCostRepo itemCostRepo;
    @Autowired
    StudentService studentService;
    @Autowired
    SemesterRepo semesterRepo;

    private static SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {

        List<RoomItemCost> roomItemCosts = itemCostRepo.findAll();

        if (roomItemCosts.isEmpty()) {
            roomItemCosts = new ArrayList<>();

            RoomItemCost itemCost = new RoomItemCost();
            itemCost.setItemName(ItemName.BROOM);
            roomItemCosts.add(itemCost);

            itemCost = new RoomItemCost();
            itemCost.setItemName(ItemName.CURTAIN);
            roomItemCosts.add(itemCost);

            itemCost = new RoomItemCost();
            itemCost.setItemName(ItemName.DUSTBIN);
            roomItemCosts.add(itemCost);

            itemCost = new RoomItemCost();
            itemCost.setItemName(ItemName.MATRES);
            roomItemCosts.add(itemCost);

            itemCost = new RoomItemCost();
            itemCost.setItemName(ItemName.TABLE);
            roomItemCosts.add(itemCost);

            itemCost = new RoomItemCost();
            itemCost.setItemName(ItemName.CHAIR);
            roomItemCosts.add(itemCost);


            itemCostRepo.save(roomItemCosts);
        }

        List<Semester> semesters = semesterRepo.findAll();

        Semester semester = null;

        if (semesters.size() >= 1){
            semester = semesters.get(0);

            semester.setSemStartDate(formatter.format(semester.getSemesterStartDate()));
            semester.setSemEndDate(formatter.format(semester.getSemesterEndDate()));
            semester.setOffSessionDate(formatter.format(semester.getOffSessionBookingStartDate()));

        }else {
            semester = new Semester();
        }

        model.addAttribute("semester" , semester);

        return "/admin/index";
    }

    @RequestMapping(value = "/zones", method = RequestMethod.GET)
    public String zones(Model model) {
        List<Zone> zoneList = zoneService.findAll();

        model.addAttribute("zoneList", zoneList);
        model.addAttribute("zoneList", zoneList);
        model.addAttribute("zone", new Zone());

        return "/admin/zone/index";
    }

    @RequestMapping(value = "/zone/createzone", method = RequestMethod.POST)
    public String createZone(@ModelAttribute @Valid Zone zone, BindingResult result, RedirectAttributes redirectAttributes,
                             Model model) {

        if (result.hasErrors()) {
            List<Zone> zoneList = zoneService.findAll();

            model.addAttribute("zoneList", zoneList);
            model.addAttribute("zone", zone);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/zone/index";
        }
        Zone newZone = zoneService.create(zone);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", newZone.getName() + " zone created");

        return "redirect:/admin/zones";
    }

    @RequestMapping(value = "/zones/view/{zoneId}", method = RequestMethod.GET)
    public String view(@PathVariable("zoneId") Long zoneId, Model model) {
        Zone zone = zoneService.findById(zoneId);
        model.addAttribute("hostel", new Hostel());
        model.addAttribute("zone", zone);

        return "/admin/zone/view";
    }

    @RequestMapping(value = "/zone/createhostel", method = RequestMethod.POST)
    public String createHostel(@ModelAttribute @Valid Hostel hostel, BindingResult result, RedirectAttributes redirectAttributes,
                               Model model) {

        if (result.hasErrors()) {

            Zone zone = zoneService.findById(hostel.getZoneId());

            model.addAttribute("hostel", hostel);
            model.addAttribute("zone", zone);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/zone/view";
        }

        Hostel newHostel = hostelService.create(hostel);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", newHostel.getName() + " hostel created");

        return "redirect:/admin/zones/view/" + hostel.getZoneId();
    }

    @RequestMapping(value = "/hostel/view/{hostelId}", method = RequestMethod.GET)
    public String viewHostel(@PathVariable("hostelId") Long hostelId, Model model) {
        Hostel hostel = hostelService.findById(hostelId);
        model.addAttribute("block", new Block());
        model.addAttribute("hostel", hostel);

        return "/admin/hostel/view";
    }

    @RequestMapping(value = "/hostels/list", method = RequestMethod.GET)
    public String viewHostels(@RequestParam(value = "searchTerm", required = false, defaultValue = "null") String searchTerm,
                              Model model) {

        List<Hostel> hostelList = hostelService.findAll();

        model.addAttribute("hostelList", hostelList);

        return "/admin/hostel/index";
    }

    @RequestMapping(value = "/block/createblock", method = RequestMethod.POST)
    public String createBlock(@ModelAttribute @Valid Block block, BindingResult result, RedirectAttributes redirectAttributes,
                              Model model) {

        if (result.hasErrors()) {
            Hostel hostel = hostelService.findById(block.getHostelId());
            model.addAttribute("block", block);
            model.addAttribute("hostel", hostel);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/hostel/view";

        }

        Block newBlock = blockService.create(block);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", newBlock.getName() + " block created");

        return "redirect:/admin/hostel/view/" + block.getHostelId();
    }

    @RequestMapping(value = "/block/view/{blockId}", method = RequestMethod.GET)
    public String viewBlock(@PathVariable("blockId") Long blockId,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model) {
        Block block = blockService.findById(blockId);
        Page<Room> roomsPage = blockService.findRooms(blockId, page, size);
        model.addAttribute("block", block);
        model.addAttribute("roomsPage", roomsPage);
        model.addAttribute("room", new Room());
        model.addAttribute("pagenatedUrl", "/admin/block/view/" + blockId);


        return "/admin/block/view";
    }

    @RequestMapping(value = "/room/createroom", method = RequestMethod.POST)
    public String createRoom(@ModelAttribute @Valid Room room, BindingResult result,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                             @RequestParam(value = "size", required = false, defaultValue = "15") int size, RedirectAttributes redirectAttributes,
                             Model model) {

        if (result.hasErrors()) {
            Block block = blockService.findById(room.getBlockId());
            Page<Room> roomsPage = blockService.findRooms(room.getBlockId(), page, size);
            model.addAttribute("block", block);
            model.addAttribute("roomsPage", roomsPage);
            model.addAttribute("room", room);
            model.addAttribute("pagenatedUrl", "/admin/block/view/" + room.getBlockId());

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/block/view";

        }

        Room newRoom = roomService.create(room);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", "Room " + newRoom.getName() + " created");

        return "redirect:/admin/block/view/" + room.getBlockId() + "?page=" + page + "&size=" + size;
    }

    @RequestMapping(value = "/room/view/{roomId}", method = RequestMethod.GET)
    public String viewRom(@PathVariable("roomId") Long roomId, Model model) {
        Room room = roomService.findById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("bed", new Bed());


        return "/admin/room/view";
    }

    @RequestMapping(value = "/room/createbed", method = RequestMethod.POST)
    public String createBed(@ModelAttribute @Valid Bed bed, BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        Room room = roomService.findById(bed.getRoomId());

        //Add new bed
        if (result.hasErrors()) {
            model.addAttribute("room", room);
            model.addAttribute("bed", bed);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/room/view";

        }

        if (room.getBeds().size() < room.getCapacity()) {
            Bed newBed = bedService.create(bed);
            redirectAttributes.addFlashAttribute("message", true);
            redirectAttributes.addFlashAttribute("content", "Bed " + newBed.getNumber() + " created");

        } else {
            redirectAttributes.addFlashAttribute("message", true);
            redirectAttributes.addFlashAttribute("content", "Room capacity exceeded ");
        }

        return "redirect:/admin/room/view/" + bed.getRoomId();
    }

    @RequestMapping(value = "/room/updatebed", method = RequestMethod.POST)
    public String updateBed(@ModelAttribute @Valid Bed bed, BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        Room room = roomService.findById(bed.getRoomId());

        //Update bed
        if (result.hasErrors()) {

            model.addAttribute("room", room);
            model.addAttribute("bed", new Bed());
            model.addAttribute("bedInDb", bed);
            model.addAttribute("flag", true);

            model.addAttribute("message", true);
            model.addAttribute("content", "Form has errors");

            return "/admin/room/view";

        }

        Bed newBed = bedService.update(bed);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", "Bed " + newBed.getNumber() + " edited");


        return "redirect:/admin/room/view/" + bed.getRoomId();
    }

    @RequestMapping(value = "/room/editbed/{bedId}", method = RequestMethod.GET)
    public String editBed(@PathVariable("bedId") Long bedId,
                          @RequestParam(value = "flag", required = false, defaultValue = "false") boolean flag,
                          Model model) {

        Bed bed = bedService.findOne(bedId);

        Room room = bed.getRoom();

        model.addAttribute("room", room);
        model.addAttribute("bed", new Bed());
        model.addAttribute("bedInDb", bed);
        model.addAttribute("flag", flag);

        return "/admin/room/view";


    }

    @RequestMapping(value = "/hostels/roomitems", method = RequestMethod.GET)
    public String roomItems(@RequestParam(value = "val", required = false, defaultValue = "0") Long val,
                            @RequestParam(value = "flag", required = false, defaultValue = "false") boolean flag, Model model) {

        List<RoomItemCost> itemCosts = itemCostRepo.findAll();

        if (flag) {
            RoomItemCost roomItemCost = itemCostRepo.findOne(val);

            model.addAttribute("flag", flag);

            model.addAttribute("itemCost", roomItemCost);
        }

        model.addAttribute("itemCosts", itemCosts);

        return "/admin/hostel/items";
    }

    @RequestMapping(value = "/hostel/itemcostupdate", method = RequestMethod.POST)
    public String updateItemCost(@ModelAttribute @Valid RoomItemCost itemCost, BindingResult result,
                                 RedirectAttributes redirectAttributes, Model model) {

        if (result.hasErrors()) {
            List<RoomItemCost> itemCosts = itemCostRepo.findAll();

            model.addAttribute("flag", true);
            model.addAttribute("itemCost", itemCost);
            model.addAttribute("itemCosts", itemCosts);

            model.addAttribute("message", true);
            model.addAttribute("content", "Provide item cost");

            return "/admin/hostel/items";
        }

        RoomItemCost roomItemCostInDb = itemCostRepo.findOne(itemCost.getId());
        roomItemCostInDb.setUnitCost(itemCost.getUnitCost());
        roomItemCostInDb.setTotalAvailable(itemCost.getTotalAvailable());

        if (roomItemCostInDb.getTotalIssued() == null){
            roomItemCostInDb.setTotalIssued(new Long(0));
        }

        roomItemCostInDb = itemCostRepo.save(roomItemCostInDb);

        redirectAttributes.addFlashAttribute("message", true);
        redirectAttributes.addFlashAttribute("content", roomItemCostInDb.getItemName() + " cost updated ");

        return "redirect:/admin/hostels/roomitems";
    }

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public String students(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                           @RequestParam(value = "search", required = false) String search,
                             Model model) {

        Page<StudentProfile> studentProfiles = null;

        if(search == null){
            studentProfiles = studentService.getAll(page , size);
        }else {
            studentProfiles = studentService.getByStudentNumber(search, page, size);
        }

        model.addAttribute("studentPage" , studentProfiles);
        model.addAttribute("pagenatedUrl", "/admin/student/list");

        return "/admin/studentList";
    }

    @RequestMapping(value = "/student/{profileId}/details", method = RequestMethod.GET)
    public String student(@PathVariable("profileId") Long profileId,
                          @RequestParam(value = "all", required = false , defaultValue = "false") boolean all , Model model) {

        StudentProfile profile = studentService.loadProfileById(profileId);
        List<RoomItem> roomItemList = studentService.getStudentRoomItems(profile.getStudent().getId() , all);

        model.addAttribute("profile" , profile);
        model.addAttribute("roomItems" , roomItemList);

        return "/admin/studentDetails";
    }

    @RequestMapping(value = "/student/item/{itemId}/issue/{profileId}", method = RequestMethod.GET)
    public String issueItem(@PathVariable("itemId") Long itemId,@PathVariable("profileId") Long profileId,
                            RedirectAttributes redirectAttributes) {

        String result = studentService.issueItem(itemId);

        if (!result.equals("SUCCESS")){
            redirectAttributes.addFlashAttribute("message", true);
            redirectAttributes.addFlashAttribute("content", result);
        }

        return "redirect:/admin/student/"+profileId+"/details";
    }
}
