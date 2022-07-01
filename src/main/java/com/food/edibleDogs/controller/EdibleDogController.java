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
import org.springframework.web.client.RestTemplate;

@Controller
public class EdibleDogController {

    @Autowired
    EdibleDogService edibleDogService;

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("message", "Hello fellow Dogs, I AM THE CONTROLLER!!! :D");
        return "home";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        String dogImageURL = edibleDogService.fetchDogImageURL();
        model.addAttribute("dogImage", dogImageURL);
        model.addAttribute("message", "Hello fellow Dogs, I AM THE CONTROLLER!!! :D");
        return "test";
    }

}
