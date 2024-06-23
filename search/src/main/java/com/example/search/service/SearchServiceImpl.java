package com.example.search.service;

import com.example.search.model.BookAuthorResponse;
import com.example.search.model.DetailsResponse;
import com.example.search.model.GeneralResponse;
import com.example.search.model.CombinedData;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.Collections;

@Service
public class SearchServiceImpl implements SearchService {

    private final RestTemplate restTemplate;

    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultBookAuthor")
    private CompletableFuture<BookAuthorResponse> getBookOrAuthor(String searchQuery) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://book-author-service/search?q=" + searchQuery;
            return restTemplate.getForObject(url, BookAuthorResponse.class);
        });
    }

    @HystrixCommand(fallbackMethod = "defaultDetails")
    private CompletableFuture<DetailsResponse> getDetails(String searchQuery) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://details-service/details/search?q=" + searchQuery;
            return restTemplate.getForObject(url, DetailsResponse.class);
        });
    }

    @Override
    public CompletableFuture<GeneralResponse> search(String query) {
        CompletableFuture<BookAuthorResponse> bookAuthorFuture = getBookOrAuthor(query);
        CompletableFuture<DetailsResponse> detailsFuture = getDetails(query);

        return bookAuthorFuture.thenCombine(detailsFuture, (bookAuthor, details) -> {
            GeneralResponse response = new GeneralResponse();
            response.setCode("200");
            response.setTimestamp(System.currentTimeMillis());
            response.setData(new CombinedData(bookAuthor, details));
            return response;
        });
    }

    private CompletableFuture<BookAuthorResponse> defaultBookAuthor(String searchQuery) {
        // Return a default or empty response
        return CompletableFuture.completedFuture(new BookAuthorResponse());
    }

    private CompletableFuture<DetailsResponse> defaultDetails(String searchQuery) {
        // Return a default or empty response
        return CompletableFuture.completedFuture(new DetailsResponse());
    }
}
