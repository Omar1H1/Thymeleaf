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

import java.util.List;
import java.util.Optional;

@Controller
public class HelloWorldController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String sayHello(Model model) {

        if (httpSession.getAttribute("useremail") != null) {
            User user = repository.findByEmail(httpSession.getAttribute("useremail").toString())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            model.addAttribute("user", user);

            return "index";
        }

        return "redirect:/signup/";
    }

    @GetMapping("/admin")
    public String login(Model model, User userInfo) {

        model.addAttribute("userInfo", userInfo);
        
        if (httpSession.getAttribute("useremail") != null) {
            Optional<User> user = repository.findByEmail(httpSession.getAttribute("useremail").toString());
            if (user.isEmpty()) {
                return "redirect:/signup/";
            }
            if (user.get().getRole().toString().equals("GigaChad")) {
                List<User> users = repository.findAll();
                model.addAttribute("users", users);

                return "admin";
            } else {
                return "redirect:/NotFound/";
            }
        }

        return "redirect:/login/";
    }

    @PostMapping("/signup")
    public String createUser(Model model, @ModelAttribute User userInfo) {
        User user = repository.save(userInfo);
        return "signup";
    }

    @GetMapping("/NotFound/")
    public String notFound() {
        return "NotFound";
    }

    @GetMapping("/*")
    public String anypath() {
        return "NotFound";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("userInfo") User userInfo) {
        repository.deleteById(userInfo.getId());
        return "redirect:/admin";
    }


}
