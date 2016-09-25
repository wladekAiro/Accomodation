package com.wladek.accomodation.web.student;

import com.wladek.accomodation.domain.accomodation.StudentProfile;
import com.wladek.accomodation.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by wladek on 9/25/16.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentService studentService;

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
}
