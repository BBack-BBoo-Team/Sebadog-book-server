package com.book.app.modules.books.service;

import com.book.app.modules.books.entity.Book;

public interface BookService {
    /**
     * 도서 등록
     * @param book
     * @return
     */
    Book addBookInfo(Book book);
}
