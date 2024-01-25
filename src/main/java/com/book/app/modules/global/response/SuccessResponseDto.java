package com.book.app.modules.global.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SuccessResponseDto<T> {

    private String status;
    private String domain;
    private T data;

    public SuccessResponseDto(String domain, T data) {
        this.status = "success";
        this.domain = domain;
        this.data = data;
    }
}
