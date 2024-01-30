package com.book.app.modules.books.dto;

import com.book.app.modules.books.entity.Book;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class BookResponseDto {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String img;
    private String status;
    private String createdBy;
    private LocalDateTime createDt;

}
