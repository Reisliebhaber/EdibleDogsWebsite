package com.food.edibleDogs.mapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class APIImageResponse {
    String id;
    String url;
    int width;
    int height;
    String mime_type;
    ArrayList<String> breeds;// TODO array?
    ArrayList<String> categories;// TODO array?
}
