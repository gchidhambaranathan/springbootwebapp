package com.example.demo.com.example.demo.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class BookService {

    @Autowired
    BookRepositary bookRepositary;

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Iterator<Book> booIterator = bookRepositary.findAll().iterator();
        booIterator.forEachRemaining(books::add);
        return books;
    }

    public void addBook(Book book) {
        bookRepositary.save(book);
    }

    public Book getBook(Long id){
        return bookRepositary.findOne(id);
    }

    public void updateBook(Book book) {
        bookRepositary.save(book);
    }

    public void deleteBook(long id) {
        bookRepositary.delete(id);
    }
}
