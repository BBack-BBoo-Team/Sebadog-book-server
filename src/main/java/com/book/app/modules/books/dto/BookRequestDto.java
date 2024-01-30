package com.book.app.modules.books.dto;

import com.book.app.modules.books.entity.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "진행 상태를 선택해주세요.")
    private Book.BookStatus status;

    private String createdBy;

    public static Book toEntity(BookRequestDto dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .img(dto.getImg())
                .createdBy(dto.getCreatedBy())
                .status(dto.getStatus())
                .build();
    }
}
