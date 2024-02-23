package com.book.app.modules.books.service.impl;

import com.book.app.modules.books.dto.BookAddRequest;
import com.book.app.modules.books.dto.BookAddResponse;
import com.book.app.modules.books.dto.BookInfoResponse;
import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.repository.BookRepository;
import com.book.app.modules.books.service.BookService;
import com.book.app.modules.global.exception.BusinessLogicException;
import com.book.app.modules.global.exception.ErrorCode.AccountErrorCode;
import com.book.app.modules.global.exception.ErrorCode.BookErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookAddResponse addBookInfo(BookAddRequest bookAddRequest) {
        Book saveBook = bookAddRequest.toEntity();
        return BookAddResponse.toResponse(bookRepository.save(saveBook));
    }

    @Override
    public BookInfoResponse getBookById(Long bookId) {
        Book bookInfo = bookRepository.findByBookId(bookId);
        if (bookInfo == null) {
            throw new BusinessLogicException(BookErrorCode.BOOK_ID_NOT_FOUND);
        }
        return BookInfoResponse.toResponse(bookInfo);
    }
}
