package com.book.app.modules.books.repository;

import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.entity.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookId(Long bookId);

    List<Book> findByStatus(BookStatus status);
}
