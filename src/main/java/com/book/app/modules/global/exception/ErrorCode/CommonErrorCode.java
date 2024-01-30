package com.book.app.modules.global.exception.ErrorCode;

import com.book.app.modules.global.Domain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "테이블 제약조건 위반입니다."),
    INVALID_VALIDATION(HttpStatus.BAD_REQUEST, "요청 데이터의 유효성 검증을 실패하였습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청하는 내용과 일치하는 데이터가 없습니다."),

    // TODO : 잘못된 URL로 접근했을 때
    // TODO : 잘못된 Http Method로 접근했을 때

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 측에서 발생한 에러입니다. 서버에 문의해주세요.");

    private final Domain domain = Domain.common;
    private final HttpStatus httpStatus;
    private final String message;
}
