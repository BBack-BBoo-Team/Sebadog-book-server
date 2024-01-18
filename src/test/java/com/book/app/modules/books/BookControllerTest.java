package com.book.app.modules.books;

import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class BookControllerTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("도서 등록 테스트")
    public void insertBookTest() {
        //given
        Book book = new Book();
        book.setBookTitle("책 제목");
        book.setBookImg("이미지경로");
        book.setAuthor("저자");
        book.setPublisher("출판사");
        book.setStatus("진행중");
        book.setCreatedBy("작성자");

        //when
        Book response = bookRepository.save(book);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals("책 제목", response.getBookTitle()),
                () -> Assertions.assertEquals("이미지경로", response.getBookImg()),
                () -> Assertions.assertEquals("저자", response.getAuthor()),
                () -> Assertions.assertEquals("출판사", response.getPublisher()),
                () -> Assertions.assertEquals("진행중", response.getStatus()),
                () -> Assertions.assertEquals("작성자", response.getCreatedBy()),
                () -> Assertions.assertFalse(response.getBookId().describeConstable().isEmpty())
        );
    }
}
