package com.book.app.modules.books.controller;

import com.book.app.modules.books.dto.BookRequestDto;
import com.book.app.modules.books.dto.BookResponseDto;
import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.service.BookService;
import com.book.app.modules.global.Domain;
import com.book.app.modules.global.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.book.app.modules.books.dto.BookRequestDto.toEntity;

@Slf4j
@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    /**
     * 도서 추가
     */
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid BookRequestDto bookRequestDto) {
        // todo : valid 체크 예외 처리 코드 추가
        log.info("book-add request = {}", bookRequestDto);
        Book book = toEntity(bookRequestDto);
        Book saveBook = bookService.addBookInfo(book);
        BookResponseDto response = Book.toResponseDto(saveBook);
        log.info("book-add response = {}", response);

        return new ResponseEntity<>(Response.success(Domain.book, response),
                HttpStatus.CREATED);

    }

}
