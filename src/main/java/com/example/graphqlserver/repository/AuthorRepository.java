package com.example.graphqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.graphqlserver.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
