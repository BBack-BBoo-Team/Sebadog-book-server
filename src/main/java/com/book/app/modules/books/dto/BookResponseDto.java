package com.book.app.modules.books.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Data
public class BookResponseDto {
    private Long bookId;
    private String bookTitle;
    private String author;
    private String publisher;
    private String bookImg;
    private String status;
    private String createId;
    private Timestamp createDt;

}
