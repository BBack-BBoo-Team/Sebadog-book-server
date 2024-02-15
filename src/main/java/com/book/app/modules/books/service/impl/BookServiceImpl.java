package com.book.app.modules.books.service.impl;

import com.book.app.modules.books.dto.BookRequestDto;
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
    public Book addBookInfo(BookRequestDto bookRequestDto) {
        Book book = bookRequestDto.toEntity();
        /* 동일한 책이 있는지 체크 로직 추가 ? */
        return bookRepository.save(book);
    }
}
