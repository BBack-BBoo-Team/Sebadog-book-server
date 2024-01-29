package com.book.app.modules.global.exception;

import com.book.app.modules.global.exception.ErrorCode.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    private final int code;
    private final String name;
    private final String message;

    private final List<ValidationError> validationError;

    private final List<ConstraintError> constraintError;

    public static ErrorResponse of(ErrorCode e, List<ValidationError> validationErrors, List<ConstraintError> constraintErrors) {
        return new ErrorResponse(
                e.getHttpStatus().value(),
                e.name(),
                e.getMessage(),
                validationErrors, constraintErrors);
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ValidationError {
        private String field;
        private Object rejectedValue;
        private String reason;

        public static ValidationError of(final FieldError e) {
            return ValidationError.builder()
                    .field(e.getField())
                    .rejectedValue(e.getRejectedValue())
                    .reason(e.getDefaultMessage())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ConstraintError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        public static ConstraintError of(final ConstraintViolation<?> v) {
            return ConstraintError.builder()
                    .propertyPath(v.getPropertyPath().toString())
                    .rejectedValue(v.getInvalidValue().toString())
                    .reason(v.getMessage())
                    .build();
        }
    }
}
