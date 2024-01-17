package com.book.app.modules.books.controller;

import com.book.app.modules.books.dto.BookDto;
import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    /**
     * 도서 추가
     */
    @PostMapping("/insertBook")
    public ResponseEntity addBook(@RequestBody BookDto bookDto) {


        return new ResponseEntity<>(new BookDto(), HttpStatus.CREATED);
    }

}
