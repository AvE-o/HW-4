package com.example.search.model;

import lombok.Data;

@Data
public class DetailsResponse {
    private String detailId;
    private String detailName;
    private int cityId; // cityId as part of the details response
}
