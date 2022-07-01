package com.food.edibleDogs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor//(onConstructor = @__(@Autowired))
public class EdibleDogService {

    public final String dogApiURL = "https://api.thedogapi.com/";

    public ArrayList<String> fetchDogImageURL() {
        ArrayList<String> dogImagesURL = new ArrayList<>();
        String dogImageURL = "";
        String requestDogImagePath = "v1/images/search?size=thumb&mime_types=jpg&format=json&order=RANDOM&limit=10";//TODO &has_breeds=false if trying to use mapper class to avoid try catch
        String requestDogImageURL = dogApiURL + requestDogImagePath;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> dogImagesResponse = restTemplate.getForEntity(requestDogImageURL, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(dogImagesResponse.getBody());
            for (JsonNode jsonDogImagesURL : root) {
                dogImagesURL.add(jsonDogImagesURL.path("url").asText());
            }
        } catch (JsonProcessingException e) {
            log.error("Error, Dog Image Response from API had an Error", e);
            throw new RuntimeException(e);
        }
        return dogImagesURL;
    }
}
