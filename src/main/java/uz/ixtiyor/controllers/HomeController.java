package uz.ixtiyor.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @RequestMapping(value = {"/", "/home"})
    public String homePage() {
        return "home";
    }

    @RequestMapping("/play/{filepath}")
    private String path(Model model, @PathVariable(name = "filepath") String path) {
        model.addAttribute("path", "/uploads/" + path);
        return "helpers/play";
    }


}
