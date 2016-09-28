package com.wladek.accomodation.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wladek
 */
@Controller
public class HomeContoller {

    @RequestMapping(value = "/admin" , method = RequestMethod.GET)
    public String getAdmin(){
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/student" , method = RequestMethod.GET)
    public String getStudent(){
        return "redirect:/student/profile";
    }

}
