package com.book.app.modules.books.entity;

import com.book.app.modules.books.dto.BookResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Book {
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

    @CreatedDate private LocalDateTime createdDt;

    @Column(nullable = false)
    private String createdBy;

    @LastModifiedDate private LocalDateTime updatedDt;

    /**
     * @Name: finishDt
     * @Description
     * 1) '진행중 혹은 진행예정'인 도서가 '진행완료' 상태가 될 때, 기록되는 일자입니다.
     * 2) '진행완료' 상태에서 '진행중, 진행예정'으로 복구한 뒤, 다시 '완료' 상태로 변경하면 일자를 업데이트합니다.
     */
    @Column(name = "FINISH_DT")
    private LocalDateTime finishDt;

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
                .createDt(book.getCreatedDt())
                .build();
    }
}
