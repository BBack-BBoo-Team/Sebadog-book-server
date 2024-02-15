package com.book.app.modules.books.service;

import com.book.app.modules.books.dto.BookRequestDto;
import com.book.app.modules.books.entity.Book;
import jakarta.validation.Valid;

public interface BookService {

    /**
     * 도서 등록
     * @param book
     * @return
     */
    Book addBookInfo(@Valid BookRequestDto book);
}
