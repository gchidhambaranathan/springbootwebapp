package com.example.demo.com.example.demo.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

@RestController
public class BookCoontroller {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public HttpEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        books.stream().forEach(BookCoontroller::addResource);
        return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }


    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public HttpEntity<Book> getBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        addResource(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public void updateBook(@RequestBody Book book, @PathVariable long id) {
         bookService.updateBook(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    public static void addResource(Book book){
        book.add(linkTo(methodOn(BookCoontroller.class).getBook(book.getUnid())).withSelfRel());
    }
}
