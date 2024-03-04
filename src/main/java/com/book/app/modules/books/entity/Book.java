package com.book.app.modules.books.entity;

import com.book.app.modules.books.dto.UpdateBookInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOK")
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

    @CreatedDate
    @Column(name="CREATE_DT")
    private LocalDateTime createDt;

    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name="UPDATE_DT")
    private LocalDateTime updateDt;

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

    public Book(String title, String author, String publisher, String img, String createdBy, BookStatus status) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.img = img;
        this.createdBy = createdBy;
        this.status = status;
    }

    public static Book of(String title, String author, String publisher, String img, String createdBy, BookStatus status) {
        return new Book(title, author, publisher, img, createdBy, status);
    }

    public void updateBook(UpdateBookInfo book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.img = book.getImg();
        this.createdBy = book.getCreatedBy();
        this.status = BookStatus.fromString(book.getStatus());
    }
}
