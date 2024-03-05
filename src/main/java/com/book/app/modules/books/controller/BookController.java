package com.book.app.modules.books.controller;

import com.book.app.modules.books.dto.*;
import com.book.app.modules.books.service.BookService;
import com.book.app.modules.global.Domain;
import com.book.app.modules.global.Response;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 도서 등록
     * @param saveBook
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid SaveBook saveBook) {
        SaveBookInfo result = bookService.addBookInfo(saveBook);
        return new ResponseEntity<>(Response.success(Domain.book, result),
                HttpStatus.CREATED);
    }

    /**
     * 도서 상세 조회
     * @param bookId
     * @return
     */
    @GetMapping("/details/{id}")
    public ResponseEntity getBookDetails(@PathVariable("id") Long bookId) {
        BookInfo result = bookService.getBookById(bookId);
        return new ResponseEntity<>(
                Response.success(Domain.book, result),
                HttpStatus.OK);
    }

    /**
     * 도서 정보 수정
     * @param updateBook
     * @return
     */
    @PostMapping("/update/{id}")
    public ResponseEntity updateBookInfo(@RequestBody @Valid UpdateBookInfo updateBook, @PathVariable("id") Long bookId) {
        UpdateBookInfo result = bookService.updateBookInfo(updateBook, bookId);
        return new ResponseEntity<>(Response.success(Domain.book, result),
                HttpStatus.OK);
    }

    /**
     * 도서 정보 삭제
     * @param bookId
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookInfo(@PathVariable("id") Long bookId) {
        bookService.deleteBookInfo(bookId);
        return new ResponseEntity<>(Response.success(Domain.book),
                HttpStatus.NO_CONTENT);
    }


    /**
     * 도서 목록 전체 조회
     * @return
     */
    @GetMapping("/listAll")
    public ResponseEntity getBookListAll() {
        List<BookInfo> bookList = bookService.getBookListAll();
        return new ResponseEntity<>(
                Response.success(Domain.book, bookList),
                HttpStatus.OK);
    }

    /**
     * 도서 목록 전체 조회 > 검색 조건 추가
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity getBookList(@RequestBody Map<String, String> status) {
        List<BookInfo> bookList = bookService.getBookList(status);
        return new ResponseEntity<>(
                Response.success(Domain.book, bookList),
                HttpStatus.OK);
    }
}
