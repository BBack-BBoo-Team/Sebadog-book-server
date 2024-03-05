package com.book.app.modules.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {

    private enum Status {success, fail}

    private Status status;

    private Domain domain;

    private Object data;

    private Object errors;

    private LocalDateTime res_time;

    public static Response success(Domain domain, Object data) {
        return Response.builder()
                .status(Status.success)
                .domain(domain)
                .data(data)
                .res_time(LocalDateTime.now())
                .build();
    }

    public static Response success(Domain domain) {
        return Response.builder()
                .status(Status.success)
                .domain(domain)
                .res_time(LocalDateTime.now())
                .build();
    }

    public static Response fail(Domain domain, Object error) {
        return Response.builder()
                .status(Status.fail)
                .domain(domain)
                .errors(error)
                .res_time(LocalDateTime.now())
                .build();
    }

    public static Response fail(Object error) {
        return Response.builder()
                .status(Status.fail)
                .errors(error)
                .res_time(LocalDateTime.now())
                .build();
    }

}
