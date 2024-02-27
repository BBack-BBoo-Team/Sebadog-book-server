package com.book.app.modules.global.exception.ErrorCode;

import com.book.app.modules.global.Domain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookErrorCode implements ErrorCode {
    BOOK_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "Book ID에 해당하는 도서 정보를 찾을 수 없습니다."),
    STATUS_BAD_REQUEST(HttpStatus.BAD_REQUEST, "도서상태는 진행예정, 진행중, 진행완료 중 한 가지에 해당 합니다.");

    private final Domain domain = Domain.book;

    private final HttpStatus httpStatus;
    private final String message;


}
