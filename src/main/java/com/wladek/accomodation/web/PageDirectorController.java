package com.wladek.accomodation.web;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.domain.enumeration.UserRole;
import com.wladek.accomodation.service.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wladek on 7/6/15.
 */
@Controller
public class PageDirectorController {

    @RequestMapping(value = "/url-processor" , method = RequestMethod.GET)
    public String redirect(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDetails.getUser();

        if (user.getUserRole().equals(UserRole.ADMIN)){
            return "redirect:/admin/home" ;
        }

        if (user.getUserRole().equals(UserRole.STUDENT)){
            return "redirect:/student/profile/"+user.getId();
        }

        return "redirect:/";

    }
}
