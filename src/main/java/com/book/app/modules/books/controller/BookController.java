package com.book.app.modules.books.controller;

import com.book.app.modules.books.dto.BookAddRequest;
import com.book.app.modules.books.dto.BookAddResponse;
import com.book.app.modules.books.dto.BookInfoResponse;
import com.book.app.modules.books.service.BookService;
import com.book.app.modules.global.Domain;
import com.book.app.modules.global.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid BookAddRequest bookAddRequest) {
        BookAddResponse result = bookService.addBookInfo(bookAddRequest);
        return new ResponseEntity<>(Response.success(Domain.book, result),
                HttpStatus.CREATED);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity getBookDetails(@PathVariable("id") Long bookId) {
        BookInfoResponse result = bookService.getBookById(bookId);
        return new ResponseEntity<>(
                Response.success(Domain.book, result),
                HttpStatus.OK);
    }

}
