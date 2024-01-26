package com.book.app.modules.books.entity;

import com.book.app.modules.books.dto.BookRequestDto;
import com.book.app.modules.books.dto.BookResponseDto;
import com.book.app.modules.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus status;


    public enum BookStatus {
        BEFORE_PROGRESS("진행예정"),
        IN_PROGRESS("진행중"),
        COMPLETED("진행완료");

        private String description;

        BookStatus(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }

    }


    public static BookResponseDto toResponseDto(Book book) {
        return BookResponseDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .img(book.getImg())
                .createdBy(book.getCreatedBy())
                .status(book.getStatus().toString())
                .createDt(book.getCreateDt())
                .build();
    }
}
