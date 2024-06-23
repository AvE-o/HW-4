package com.example.search.model;

import lombok.Data;

@Data
public class BookAuthorResponse {
    private String id;
    private String name;
    private String type; // Book or Author
}
