package com.example.demo.book;


import org.springframework.data.repository.CrudRepository;

public interface BookRepositary extends CrudRepository<Book, Long> {
}
