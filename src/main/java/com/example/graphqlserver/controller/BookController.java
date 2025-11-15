package com.example.graphqlserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.dto.output.AddBookPayload;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book bookByISBN(@Argument String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    @MutationMapping
    public AddBookPayload addBook(@Argument AddBookInput input) {

        Author author = authorRepository.findById(input.authorId()).orElse(null);
        if (author == null) {
            throw new IllegalArgumentException("Author with ID " + input.authorId() + " does not exist");
        }

        Book newBook = new Book();
        newBook.setIsbn(input.isbn());
        newBook.setTitle(input.title());
        newBook.setAuthor(author);

        Book saved = bookRepository.save(newBook);

        return new AddBookPayload(saved);
    }

    @QueryMapping
    public List<Book> booksByAuthorId(@Argument Integer authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @QueryMapping
    public List<Book> booksByTitleSubstring(@Argument String substring) {
        if (substring == null || substring.isBlank()) {
            return List.of();
        }
        return bookRepository.findByTitleContainingIgnoreCase(substring);
    }

    @MutationMapping
    public String deleteBookByIsbn(@Argument String isbn) {
        if (isbn == null || isbn.isBlank()) {
            return null;
        }

        return bookRepository.findById(isbn)
                .map(book -> {
                    try {
                        bookRepository.delete(book);
                        return isbn; 
                    } catch (Exception ex) {
                        return null;
                    }
                })
                .orElse(null);
    }

    @QueryMapping
    public List<String> bookTitlesByAuthorFirstName(@Argument String firstName) {
        if (firstName == null || firstName.isBlank()) {
            return List.of();
        }

        var authors = authorRepository.findByFirstNameIgnoreCase(firstName);
        if (authors.isEmpty()) {
            return List.of();
        }

        var authorIds = authors.stream()
                .map(Author::getId)
                .toList();

        var books = bookRepository.findByAuthorIdIn(authorIds);

        return books.stream()
                .map(Book::getTitle)
                .toList();
    }
}