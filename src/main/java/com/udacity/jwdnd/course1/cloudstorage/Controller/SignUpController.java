package com.udacity.jwdnd.course1.cloudstorage.Controller;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Controller
@RequestMapping("/signup")
public class SignUpController {
    private UserService userService;
    public SignUpController(UserService userService) {
        this.userService = userService;
    }
    public boolean isPasswordValid(String password) {
        if (password.length() >= 8) {
            Pattern letter = Pattern.compile("[a-z]");
            Pattern upperCaseLetter = Pattern.compile("[A-Z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?.{}\\[\\]~-]");
            Matcher hasLetter = letter.matcher(password);
            Matcher hasUpperCaseLetter = upperCaseLetter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);
            return hasLetter.find() && hasUpperCaseLetter.find() && hasDigit.find() && hasSpecial.find();
        } else
            return false;
    }
    @GetMapping
    public String signUpView() {
        return "signup";
    }
    @PostMapping
    public String signUp(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model) {
        String signupErrorMessage = null;
        if (!userService.isUserAvailable(user.getUserName())) {
            signupErrorMessage = "The user name already is exist";
        }
        if(!isPasswordValid(user.getPassword())){
            signupErrorMessage = "Your password must to have: \n At least 8 characters \n 1 digit \n 1 special character" +
                    "\n 1 lower case letter \n 1 upper case letter";
        }
        if (signupErrorMessage == null) {
            int rowsAdded = userService.create(user);
            if (rowsAdded < 0) {
                signupErrorMessage = "There was an Error when sign up. please try again";
            }
        }
        if (signupErrorMessage == null) {
            model.addAttribute("signupSuccess", true);
            redirectAttributes.addFlashAttribute("success", true);
            //return "signup";
            //return "redirect:/login";
        }
        if (signupErrorMessage != null) {
            model.addAttribute("error", signupErrorMessage);
            return "signup";
        }
        return "redirect:/login?signup=true";
    }
}

