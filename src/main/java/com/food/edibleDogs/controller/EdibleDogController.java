package com.food.edibleDogs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class EdibleDogController {

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("message", "Hello fellow Dogs, I AM THE CONTROLLER!!! :D");
        return "home";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        //add Picture Request here
        RestTemplate restTemplate = new RestTemplate();
        String dogApiURL = "https://api.thedogapi.com/";
        String dogImageURL = "";//"https://cdn2.thedogapi.com/images/jtrRacj_g.jpg";
        ResponseEntity<String> dogImagesResponse = restTemplate.getForEntity(dogApiURL + "v1/images/search?size=med&" +
                "mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1", String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(dogImagesResponse.getBody());
            JsonNode jsonDogImageURL = root.get(0).path("url");
            dogImageURL = jsonDogImageURL.asText();
        } catch (JsonProcessingException e) {
            System.out.println("Error, Dog Image Response from API had an Error");
            throw new RuntimeException(e);
        }
        System.out.println(dogImagesResponse.getBody());
        //
        model.addAttribute("dogImage", dogImageURL);
        model.addAttribute("message", "Hello fellow Dogs, I AM THE CONTROLLER!!! :D");
        return "test";
    }

}
