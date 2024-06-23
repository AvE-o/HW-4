package com.example.demo.service;

import com.example.demo.model.Author;

import java.util.List;

public interface AuthorService {
    Author addAuthor(Author author);
    Author findAuthorById(Long id);
    List<Author> findAllAuthors();
}
