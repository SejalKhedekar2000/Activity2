package com.example.graphqlserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.graphqlserver.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findByLastNameIgnoreCase(String lastName);

    List<Author> findByFirstNameIgnoreCase(String firstName);

}
