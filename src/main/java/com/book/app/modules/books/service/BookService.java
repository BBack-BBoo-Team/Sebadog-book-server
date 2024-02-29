package com.book.app.modules.books.service;

import com.book.app.modules.books.dto.SaveBook;
import com.book.app.modules.books.dto.BookInfo;
import jakarta.validation.Valid;

public interface BookService {

    /**
     * 도서 등록
     * @param book
     * @return
     */
    BookInfo addBookInfo(@Valid SaveBook book);

    /**
     * 도서 상세 정보 조회
     * @param bookId
     * @return
     */
    BookInfo getBookById(Long bookId);

    /**
     * 도서 정보 수정
     *
     * @param updateBook
     * @param bookId
     * @return
     */
    BookInfo updateBookInfo(@Valid BookInfo updateBook, Long bookId);

    /**
     * 도서 정보 삭제
     *
     * @param bookId
     */
    void deleteBookInfo(Long bookId);
}
