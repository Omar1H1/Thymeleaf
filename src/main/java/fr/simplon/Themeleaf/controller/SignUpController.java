package fr.simplon.Themeleaf.controller;


import fr.simplon.Themeleaf.models.User;
import fr.simplon.Themeleaf.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/signup")
public class SignUpController {


    @Autowired
    private UserRepository repository;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String getUsers(Model model) {

        model.addAttribute("userInfo", new User());
        return "signup";
    }

    @PostMapping("/")
    public String createUser(Model model, @ModelAttribute User userInfo) {
        User user = repository.save(userInfo);
        httpSession.setAttribute("useremail", user.getEmail());
        return "redirect:/";
    }
}