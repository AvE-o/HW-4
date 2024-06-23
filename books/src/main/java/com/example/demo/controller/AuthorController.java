package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import com.example.demo.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        if (author.getName() == null || author.getName().isEmpty()) {
            throw new BadRequestException("Name is required.");
        }
        return ResponseEntity.ok(authorService.addAuthor(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(authorService.findAuthorById(id));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Author not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        try {
            List<Author> authors = authorService.findAllAuthors();
            return ResponseEntity.ok(authors);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Author not found");
        }

    }
}
