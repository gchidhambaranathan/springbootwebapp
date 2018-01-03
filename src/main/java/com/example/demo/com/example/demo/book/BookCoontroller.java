package com.example.demo.com.example.demo.book;


import com.example.demo.error.ErrorMessage;
import com.example.demo.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

@RestController
public class BookCoontroller {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET,produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        books.stream().forEach(BookCoontroller::addResource);
        return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }


    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        if(book == null){
            throw new EntityNotFoundException(id);
        }
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


   /* @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(HttpServletRequest req, EntityNotFoundException e){
        System.out.println("Called...");
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setMessage(e.getMessage());
        errorMessage.setId(e.getId());
        errorMessage.setUrl(req.getRequestURI());
        return new ResponseEntity(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}
