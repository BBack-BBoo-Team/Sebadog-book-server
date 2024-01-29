package com.book.app.modules.global.exception.ErrorCode;

import com.book.app.modules.global.Domain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AccountErrorCode implements ErrorCode {
    // AUTH
    USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없는 회원입니다."),

    // ACCOUNT
    UID_NOT_FOUND(HttpStatus.NOT_FOUND, "UID에 해당하는 사용자를 찾을 수 없습니다."),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");

    private final Domain domain = Domain.account;

    private final HttpStatus httpStatus;
    private final String message;
}
