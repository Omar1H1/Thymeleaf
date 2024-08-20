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

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("userInfo", new User());
        return "login";
    }

    @PostMapping("/")
    public String loginPost(Model model, @ModelAttribute User userInfo) {
        Optional<User> userToFind = repository.findByEmail(userInfo.getEmail());

        if (userToFind.isPresent()) {
            if (userInfo.getPassword().equals(userToFind.get().getPassword())) {
                httpSession.setAttribute("useremail", userToFind.get().getEmail());
                return "redirect:/";
            } else {
                return ":/NotFound";
            }
        }
        return ":/NotFound";
    }


}
