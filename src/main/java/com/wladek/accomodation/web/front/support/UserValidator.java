package com.wladek.accomodation.web.front.support;

import com.wladek.accomodation.domain.User;
import com.wladek.accomodation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author wladek
 */
@Component
public class UserValidator {

    @Autowired
    UserRepository repository;

    public boolean validateNewUser(User user, BindingResult result) {
        User existingUser = repository.findByEmail(user.getEmail());
        if(existingUser != null) {
            result.rejectValue("email", "user.email.duplicate", "Email already registered.");
        }

        existingUser = repository.findByLoginId(user.getLoginId());
        if(existingUser != null) {
            result.rejectValue("loginId", "user.loginId.duplicate", "Username already registered.");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())){
            result.rejectValue("password", "user.password.duplicate", "Password and confirm password do not match");
            result.rejectValue("confirmPassword", "user.confirmPassword.duplicate", "Password and confirm password do not match");
        }

        return result.hasErrors();
    }
}
