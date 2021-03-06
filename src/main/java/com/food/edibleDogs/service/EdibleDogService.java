package com.food.edibleDogs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    private ArrayList<String> fetchDogImageURL(String requestDogImagePath) {
        ArrayList<String> dogImagesURL = new ArrayList<>();
        String dogImageURL = "";
        String requestDogImageURL = dogApiURL + requestDogImagePath;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.set("x-api-key", "cbc56a7b-0c7d-44f7-8aa9-37a6791daf31");
        HttpEntity<Void> requestEntity = new HttpEntity<>(header);
        ResponseEntity<String> dogImagesResponse = restTemplate.exchange(requestDogImageURL, HttpMethod.GET, requestEntity
        ,String.class);
        //restTemplate.getForEntity(requestDogImageURL, String.class);
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
    public ArrayList<String> fetchDogImageUrlDesc(){
        return fetchDogImageURL("v1/images/search?size=med&mime_types=jpg&format=json&order=DESC&limit=25");
    }
    public ArrayList<String> fetchDogImageUrlRandom(){
        return fetchDogImageURL("v1/images/search?size=med&mime_types=jpg&format=json&order=RANDOM&limit=10");
    }
}
