package com.book.app.modules.books.dto;

import com.book.app.modules.books.entity.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotBlank(message = "도서 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "저자를 입력해주세요.")
    private String author;

    @NotBlank(message = "출판사를 입력해주세요.")
    private String publisher;

    @NotBlank(message = "도서 이미지를 등록해주세요.")
    private String img;

    private String status;

    private String createdBy;

    public Book toEntity() {
        return Book.of(this.title, this.author, this.publisher, this.img, this.createdBy, this.status);
    }
}
