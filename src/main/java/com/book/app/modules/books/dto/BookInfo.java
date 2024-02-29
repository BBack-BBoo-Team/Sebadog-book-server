package com.book.app.modules.books.dto;

import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.entity.BookStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookInfo {
    @JsonProperty("book_id")
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String img;
    private String status;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("create_dt")
    private LocalDateTime createDt;

    public static BookInfo toResponse(Book book) {
        return BookInfo.builder()
                .bookId(book.getBookId())
                .img(book.getImg())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .status(String.valueOf(book.getStatus()))
                .createdBy(book.getCreatedBy())
                .createDt(book.getCreateDt())
                .build();
    }
}
