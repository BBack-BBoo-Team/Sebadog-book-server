package com.book.app.modules.books.dto;

import lombok.Getter;

@Getter
public class BookRequestDto {
    private String bookTitle;

    private String author;

    private String publisher;

    private String bookImg;

    private String status;

    private String createId;
}
