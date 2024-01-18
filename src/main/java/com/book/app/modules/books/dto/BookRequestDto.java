package com.book.app.modules.books.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotBlank(message = "도서 제목을 입력해주세요.")
    private String bookTitle;

    @NotBlank(message = "저자를 입력해주세요.")
    private String author;

    @NotBlank(message = "출판사를 입력해주세요.")
    private String publisher;

    @NotBlank(message = "도서 제목을 입력해주세요.")
    private String bookImg;

    @NotBlank(message = "진행 상태를 선택해주세요.")
    private String status;

    private String createdBy;
}
