package com.example.demo.book;


import com.example.demo.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

@RestController
public class BookCoontroller {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        books.stream().forEach(BookCoontroller::addResource);
        return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);
    }

    @PostMapping(value = "/books")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }


    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = bookService.getBook(id);
        if(book == null){
            throw new EntityNotFoundException(id);
        }
        addResource(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping(value = "/books/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable long id) {
         bookService.updateBook(book);
    }

    @DeleteMapping(value = "/books/{id}")
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
