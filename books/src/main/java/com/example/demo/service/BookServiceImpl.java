package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.BookAuthor;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookAuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService{


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }


    @Override
    @Transactional
    public Book addBook(Book book, List<Long> authorIds) {
        Set<BookAuthor> bookAuthors = authorIds.stream()
                .map(authorId -> {
                    Author author = authorRepository.findById(authorId)
                            .orElseThrow(() -> new RuntimeException("Author not found " + authorId));
                    BookAuthor bookAuthor = new BookAuthor();
                    bookAuthor.setBook(book);
                    bookAuthor.setAuthor(author);
                    return bookAuthor;
                })
                .collect(Collectors.toSet());

        book.setBookAuthors(bookAuthors);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
