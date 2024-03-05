package com.book.app.modules.books.service;

import com.book.app.modules.books.dto.BookInfo;
import com.book.app.modules.books.dto.SaveBook;
import com.book.app.modules.books.dto.SaveBookInfo;
import com.book.app.modules.books.dto.UpdateBookInfo;
import jakarta.validation.Valid;

public interface BookService {

    /**
     * 도서 등록
     * @param book
     * @return
     */
    SaveBookInfo addBookInfo(@Valid SaveBook book);

    /**
     * 도서 상세 정보 조회
     * @param bookId
     * @return
     */
    BookInfo getBookById(Long bookId);

    /**
     * 도서 정보 수정
     *
     * @param updateBookInfo
     * @param bookId
     * @return
     */
    UpdateBookInfo updateBookInfo(@Valid UpdateBookInfo updateBookInfo, Long bookId);

    /**
     * 도서 정보 삭제
     *
     * @param bookId
     */
    void deleteBookInfo(Long bookId);
}
