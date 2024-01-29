package com.book.app.modules.global.exception;

import com.book.app.modules.global.exception.ErrorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessLogicException extends RuntimeException {

    private final ErrorCode errorCode;
}
