package com.food.edibleDogs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.edibleDogs.service.EdibleDogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class EdibleDogController {

    @Autowired
    EdibleDogService edibleDogService;
/*
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("message", "Hello fellow Dogs, I AM THE CONTROLLER!!! :D");
        return "home";
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("message", "Hello fellow Dogs, I AM THE CONTROLLER!!! :D");
        return "index";
    }*/

    @RequestMapping("/login")
    public String loginPage(){
        return "auth-login";
    }

    @RequestMapping("/diagram")
    public String loginSubmit(){
        return "/pages/landing_page";
    }

    @GetMapping("/home")
    public String fetchHomePage(Model model) {
        //String dogImageURL = edibleDogService.fetchDogImageURL().get(3);
        model.addAttribute("dogImages", edibleDogService.fetchDogImageUrlDesc());
        return "/pages/home";
    }
    @RequestMapping("/dogs")
    public String fetchDogs(Model model){
        model.addAttribute("dogImages", edibleDogService.fetchDogImageUrlRandom());
        return "/pages/dogs";
    }

}
