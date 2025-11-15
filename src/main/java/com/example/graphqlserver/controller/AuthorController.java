package com.example.graphqlserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AddAuthorPayload;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public Author authorById(@Argument Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public AddAuthorPayload addAuthor(@Argument AddAuthorInput input) {

        Author author = new Author();
        author.setFirstName(input.firstName());
        author.setLastName(input.lastName());

        Author saved = authorRepository.save(author);

        return new AddAuthorPayload(saved);
    }

    @QueryMapping
    public List<Author> authorsByLastName(@Argument String lastName) {
        if (lastName == null || lastName.isBlank()) {
            return List.of();
        }
        return authorRepository.findByLastNameIgnoreCase(lastName);
    }

    @MutationMapping
    public String updateAuthorLastName(@Argument Integer id,
                                       @Argument String newLastName) {
        if (id == null || newLastName == null || newLastName.isBlank()) {
            return null;
        }

        return authorRepository.findById(id)
                .map(author -> {
                    String oldLastName = author.getLastName();
                    author.setLastName(newLastName);
                    try {
                        authorRepository.save(author);
                        return oldLastName; 
                    } catch (Exception ex) {
                        return null;
                    }
                })
                .orElse(null);
    }
}