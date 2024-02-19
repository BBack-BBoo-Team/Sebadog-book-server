package com.book.app.modules.books.service;

import com.book.app.modules.books.dto.BookAddRequest;
import com.book.app.modules.books.dto.BookAddResponse;
import jakarta.validation.Valid;

public interface BookService {

    /**
     * 도서 등록
     *
     * @param book
     * @return
     */
    BookAddResponse addBookInfo(@Valid BookAddRequest book);
}
