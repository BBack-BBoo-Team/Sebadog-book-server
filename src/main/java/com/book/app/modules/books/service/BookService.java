package com.book.app.modules.books.service;

import com.book.app.modules.books.dto.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

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

    /**
     * 도서 목록 전체 조회
     * @return
     */
    List<BookInfo> getBookListAll();

    /**
     * 도서 목록 전체 조회 > 검색 조건 추가
     * @param requestDto
     * @return
     */
    List<BookInfo> getBookList(Map<String, String> requestDto);
}
