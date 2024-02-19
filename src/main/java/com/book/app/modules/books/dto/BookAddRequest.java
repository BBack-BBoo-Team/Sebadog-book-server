package com.book.app.modules.books.dto;

import com.book.app.modules.books.entity.Book;
import com.book.app.modules.books.entity.BookStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookAddRequest {
    @NotBlank(message = "도서 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "저자를 입력해주세요.")
    private String author;

    @NotBlank(message = "출판사를 입력해주세요.")
    private String publisher;

    @NotBlank(message = "도서 이미지를 등록해주세요.")
    private String img;

    @NotBlank(message = "도서 상태를 선택해주세요.")
    private String status;

    @JsonProperty("created_by")
    private String createdBy;

    public Book toEntity() {
        return Book.of(this.title, this.author, this.publisher, this.img, this.createdBy, BookStatus.fromString(this.status));
    }
}
