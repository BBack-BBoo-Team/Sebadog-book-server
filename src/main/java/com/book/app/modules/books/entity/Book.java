package com.book.app.modules.books.entity;

import com.book.app.modules.books.dto.BookRequestDto;
import com.book.app.modules.books.dto.BookResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private Long bookId;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String bookImg;

    @Column
    private Timestamp createDt;

    @Column(nullable = false)
    private String createId;

    @Column(nullable = false)
    private String status;

    @Column
    private String finishDt;

    public static Book toEntity(BookRequestDto dto) {
        return Book.builder()
                .bookTitle(dto.getBookTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .bookImg(dto.getBookImg())
                .createId(dto.getCreateId())
                .status(dto.getStatus())
                .build();
    }

    public static BookResponseDto toResponseDto(Book book) {
        return BookResponseDto.builder()
                .bookId(book.getBookId())
                .bookTitle(book.getBookTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .bookImg(book.getBookImg())
                .createId(book.getCreateId())
                .status(book.getStatus())
                .createDt(book.getCreateDt())
                .build();
    }
}
