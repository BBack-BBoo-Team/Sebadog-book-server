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

    @JsonProperty("update_dt")
    private LocalDateTime updateDt;

    public static BookInfo toDetailResponse(Book book) {
        return BookInfo.builder()
                .bookId(book.getBookId())
                .img(book.getImg())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .status(fromCode(book.getStatus()))
                .createdBy(book.getCreatedBy())
                .createDt(book.getCreateDt())
                .updateDt(book.getUpdateDt())
                .build();
    }
}
