package com.book.app.modules.books.service.impl;

import com.book.app.modules.books.dto.SaveBook;
import com.book.app.modules.books.dto.BookInfo;
import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.entity.BookStatus;
import com.book.app.modules.books.repository.BookRepository;
import com.book.app.modules.books.service.BookService;
import com.book.app.modules.global.exception.BusinessLogicException;
import com.book.app.modules.global.exception.ErrorCode.BookErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookInfo addBookInfo(SaveBook bookInfoRequest) {
        if (!isValidStatus(bookInfoRequest.getStatus())) {
            throw new BusinessLogicException(BookErrorCode.STATUS_BAD_REQUEST);
        }
        Book saveBook = bookInfoRequest.toEntity();

        return BookInfo.toResponse(bookRepository.save(saveBook));
    }

    @Override
    public BookInfo getBookById(Long bookId) {
        Book bookInfo = bookRepository.findByBookId(bookId);
        if (bookInfo == null) {
            throw new BusinessLogicException(BookErrorCode.BOOK_ID_NOT_FOUND);
        }
        return BookInfo.toResponse(bookInfo);
    }

    @Override
    @Transactional
    public BookInfo updateBookInfo(@Valid BookInfo updateBookInfo, Long bookId) {
        Book book = findBookInfo(bookId);

        return BookInfo.toResponse(bookRepository.save(book));
    }


    @Override
    public void deleteBookInfo(Long bookId) {
        Book bookInfo = findBookInfo(bookId);
        bookRepository.delete(bookInfo);
    }


    private boolean isValidStatus(String status) {
        return status.equals("진행중") || status.equals("진행예정") || status.equals("진행완료");
    }

    private Book findBookInfo(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessLogicException(BookErrorCode.BOOK_ID_NOT_FOUND));
    }
}
