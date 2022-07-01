package com.food.edibleDogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EdibleDogController {

    @GetMapping("/home")
    public String getHome(Model model){
        model.addAttribute("message", "Hello fellow Dogs, I AM THE CONTROLLER!!! :D");
        return "home";
    }
}
