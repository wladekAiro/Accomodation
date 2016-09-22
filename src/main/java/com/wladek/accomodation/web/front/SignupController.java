package com.wladek.accomodation.web.front;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.service.UserService;
import com.wladek.accomodation.web.front.support.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
public class SignupController {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users/signupform", method = RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "/front/users/signupform";
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String newUser(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validateNewUser(user, result);
        if(result.hasErrors()) {
            return "/front/users/signupform";
        }

        userService.addNewUser(user);

        return "redirect:/users/loginform";
    }

}
