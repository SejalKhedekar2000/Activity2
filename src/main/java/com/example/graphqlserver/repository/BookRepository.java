package com.example.graphqlserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.graphqlserver.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {


    List<Book> findByAuthorId(Integer authorId);

 
    List<Book> findByTitleContainingIgnoreCase(String substring);

    List<Book> findByAuthorIdIn(List<Integer> authorIds);

}