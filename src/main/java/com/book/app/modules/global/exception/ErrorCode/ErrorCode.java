package com.book.app.modules.global.exception.ErrorCode;

import com.book.app.modules.global.Domain;
import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String name();
    HttpStatus getHttpStatus();
    String getMessage();
    Domain getDomain();
}
