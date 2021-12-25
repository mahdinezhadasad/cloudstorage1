package com.udacity.jwdnd.course1.cloudstorage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String loginView(@RequestParam(required = false) String message,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest req) {
        if (message != null)
            model.addAttribute("param.message", message);
        return "login";
    }
}
