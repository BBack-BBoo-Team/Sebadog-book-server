package com.book.app.modules.books.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String bookImg;

    @Column(nullable = false)
    private Timestamp createDt;

    @Column(nullable = false)
    private String createId;

    @Column(nullable = false)
    private String status;

    @Column
    private String finishDt;
}
