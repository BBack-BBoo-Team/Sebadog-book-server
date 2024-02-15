package com.book.app.modules.books;

import com.book.app.modules.books.dto.BookRequestDto;
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
        BookRequestDto baseBook = new BookRequestDto();
        baseBook.setTitle("책 제목");
        baseBook.setImg("이미지경로");
        baseBook.setAuthor("저자");
        baseBook.setPublisher("출판사");
        baseBook.setStatus(Book.BookStatus.IN_PROGRESS);
        baseBook.setCreatedBy("작성자");

        Book book = baseBook.toEntity();

        //when
        Book response = bookRepository.save(book);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals("책 제목", response.getTitle()),
                () -> Assertions.assertEquals("이미지경로", response.getImg()),
                () -> Assertions.assertEquals("저자", response.getAuthor()),
                () -> Assertions.assertEquals("출판사", response.getPublisher()),
                () -> Assertions.assertEquals(Book.BookStatus.IN_PROGRESS, response.getStatus()),
                () -> Assertions.assertEquals("작성자", response.getCreatedBy()),
                () -> Assertions.assertFalse(response.getBookId().describeConstable().isEmpty())
        );
    }
}
