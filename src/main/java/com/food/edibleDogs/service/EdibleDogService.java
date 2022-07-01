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

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor//(onConstructor = @__(@Autowired))
public class EdibleDogService {

    public final String dogApiURL = "https://api.thedogapi.com/";

    public String fetchDogImageURL() {
        String dogImageURL = "";
        String requestDogImagePath = "v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1";
        String requestDogImageURL = dogApiURL + requestDogImagePath;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> dogImagesResponse = restTemplate.getForEntity(requestDogImageURL, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(dogImagesResponse.getBody());
            JsonNode jsonDogImageURL = root.get(0).path("url");
            dogImageURL = jsonDogImageURL.asText();
        } catch (JsonProcessingException e) {
            log.error("Error, Dog Image Response from API had an Error", e);
            throw new RuntimeException(e);
        }
        return dogImageURL;
    }
}
