package com.example.search.model;

import lombok.Data;

@Data
public class GeneralResponse {
    private String code;
    private long timestamp;
    private CombinedData data;
}
