package com.book.app.modules.global.exception.ErrorCode;

import com.book.app.modules.global.Domain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookErrorCode implements ErrorCode {
    BOOK_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "Book ID에 해당하는 도서 정보를 찾을 수 없습니다.");

    private final Domain domain = Domain.book;

    private final HttpStatus httpStatus;
    private final String message;


}
