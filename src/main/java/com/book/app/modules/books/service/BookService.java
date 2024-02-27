package com.book.app.modules.books.service;

import com.book.app.modules.books.dto.BookAddRequest;
import com.book.app.modules.books.dto.BookAddResponse;
import com.book.app.modules.books.dto.BookInfoResponse;
import jakarta.validation.Valid;

public interface BookService {

    /**
     * 도서 등록
     *
     * @param book
     * @return
     */
    BookAddResponse addBookInfo(@Valid BookAddRequest book);

    /**
     * 도서 상세 정보 조회
     * @param bookId
     * @return
     */
    BookInfoResponse getBookById(Long bookId);
}
