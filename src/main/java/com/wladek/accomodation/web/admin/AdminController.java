package com.wladek.accomodation.web.admin;

import com.wladek.accomodation.domain.accomodation.Hostel;
import com.wladek.accomodation.domain.accomodation.Zone;
import com.wladek.accomodation.domain.enumeration.Gender;
import com.wladek.accomodation.domain.enumeration.ZoneCode;
import com.wladek.accomodation.service.accomodation.HostelService;
import com.wladek.accomodation.service.accomodation.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
