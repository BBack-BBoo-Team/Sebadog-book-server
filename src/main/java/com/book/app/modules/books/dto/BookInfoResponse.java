package com.book.app.modules.books.dto;

import com.book.app.modules.books.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookInfoResponse {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String img;
    private String status;
    private String createdBy;
    private LocalDateTime createDt;

    public static BookInfoResponse toResponse(Book book) {
        return BookInfoResponse.builder()
                .bookId(book.getBookId())
                .img(book.getImg())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .status(book.getStatus().getStatus())
                .createdBy(book.getCreatedBy())
                .createDt(book.getCreatedDt())
                .build();
    }
}
