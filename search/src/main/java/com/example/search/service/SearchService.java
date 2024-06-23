package com.example.search.service;

import com.example.search.model.GeneralResponse;

import java.util.concurrent.CompletableFuture;

public interface SearchService {
    CompletableFuture<GeneralResponse> search(String query);
}
