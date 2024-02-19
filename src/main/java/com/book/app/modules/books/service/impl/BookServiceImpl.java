package com.book.app.modules.books.service.impl;

import com.book.app.modules.books.dto.BookAddRequest;
import com.book.app.modules.books.dto.BookAddResponse;
import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.repository.BookRepository;
import com.book.app.modules.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
