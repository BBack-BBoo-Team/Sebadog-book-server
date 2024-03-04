package com.book.app.modules.books.dto;

import com.book.app.modules.books.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

import static com.book.app.modules.books.entity.BookStatus.fromCode;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveBookInfo {
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

    public static SaveBookInfo toResponse(Book book) {
        return SaveBookInfo.builder()
                .bookId(book.getBookId())
                .img(book.getImg())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .status(fromCode(book.getStatus()))
                .createdBy(book.getCreatedBy())
                .createDt(book.getCreateDt())
                .build();
    }
}
