package com.example.search.model;

import lombok.Data;

@Data
public class CombinedData {
    private BookAuthorResponse bookAuthor;
    private DetailsResponse details;

    public CombinedData(BookAuthorResponse bookAuthor, DetailsResponse details) {
        this.bookAuthor = bookAuthor;
        this.details = details;
    }
}
