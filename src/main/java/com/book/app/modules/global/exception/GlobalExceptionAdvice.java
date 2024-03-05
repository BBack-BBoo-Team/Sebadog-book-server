package com.book.app.modules.global.exception;

import com.book.app.modules.global.exception.ErrorCode.CommonErrorCode;
import com.book.app.modules.global.exception.ErrorCode.ErrorCode;
import com.book.app.modules.global.exception.ErrorResponse.ConstraintError;
import com.book.app.modules.global.exception.ErrorResponse.ValidationError;
import com.book.app.modules.global.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;

/**
 * Controller 전역 Exception Handler 적용
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // TODO : 잘못된 URL로 접근했을 때
    // TODO : 잘못된 Http Method로 접근했을 때

    /**
     * BusinessLogic에서 발생하는 에러 핸들러
     */
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<Response> handleBusinessLogicException(BusinessLogicException e) {
        ErrorCode error = e.getErrorCode();
        log.error("[{}] {}", error.name(), error.getMessage());
        final ErrorResponse response = ErrorResponse.of(error, null, null);
        return new ResponseEntity<>(
                Response.fail(error.getDomain(), response), error.getHttpStatus());
    }

    /**
     * 유효성 검증 에러 핸들러
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidException(MethodArgumentNotValidException e) {
        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ValidationError> info =
                fieldErrors.stream()
                        .map(ValidationError::of)
                        .toList();

        CommonErrorCode error = CommonErrorCode.INVALID_VALIDATION;
        log.error("[{}] {}", error.name(), error.getMessage());
        ErrorResponse response = ErrorResponse.of(error, info, null);
        return new ResponseEntity<>(Response.fail(response), error.getHttpStatus());
    }

    /**
     * 제약 조건 위반 에러 핸들러
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handleValidException(ConstraintViolationException e) {
        final Set<ConstraintViolation<?>> fieldErrors = e.getConstraintViolations();

        List<ConstraintError> info =
                fieldErrors.stream()
                        .map(ConstraintError::of)
                        .toList();
        CommonErrorCode error = CommonErrorCode.CONSTRAINT_VIOLATION;
        log.error("[{}] {}", error.name(), error.getMessage());
        ErrorResponse response = ErrorResponse.of(error, null, info);
        return new ResponseEntity<>(Response.fail(response), error.getHttpStatus());
    }
}
