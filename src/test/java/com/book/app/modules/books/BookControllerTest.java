package com.book.app.modules.books;

import com.book.app.infra.MockMvcTest;
import com.book.app.modules.books.dto.BookAddRequest;
import com.book.app.modules.books.service.BookService;
import com.book.app.modules.global.exception.ErrorCode.BookErrorCode;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@MockMvcTest
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    Gson gson;

    @Autowired
    BookService bookService;

    final String commonUrl = "/books";


    @DisplayName("[도서 등록] 성공")
    @Test
    void addBooks_success() throws Exception {

        BookAddRequest request = new BookAddRequest();
        request.setTitle("책 제목");
        request.setImg("이미지경로");
        request.setAuthor("저자");
        request.setPublisher("출판사");
        request.setStatus("진행예정");
        request.setCreatedBy("작성자");

        mockMvc.perform(post(commonUrl +"/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.status").value("success"))
                        .andExpect(jsonPath("$.data.title").value(request.getTitle()))
                        .andExpect(jsonPath("$.data.author").value(request.getAuthor()))
                        .andExpect(jsonPath("$.data.img").value(request.getImg()))
                        .andExpect(jsonPath("$.data.publisher").value(request.getPublisher()))
                        .andExpect(jsonPath("$.data.status").value(request.getStatus()))
                        .andExpect(jsonPath("$.data.createdBy").value(request.getCreatedBy()));
    }

    @DisplayName("[도서 등록] 실패 - 존재하지 않는 도서 진행 상태")
    @Test
    void addBooks_fail_not_found_status() throws Exception {

        BookAddRequest request = new BookAddRequest();
        request.setTitle("책 제목");
        request.setImg("이미지경로");
        request.setAuthor("저자");
        request.setPublisher("출판사");
        request.setStatus("진행할거");
        request.setCreatedBy("작성자");

        mockMvc.perform(post(commonUrl +"/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.errors.name").value(BookErrorCode.STATUS_BAD_REQUEST.name())) // 예상되는 오류 코드
                .andExpect(jsonPath("$.errors.message").value(BookErrorCode.STATUS_BAD_REQUEST.getMessage())); // 예상되는 오류 메시지
    }


}
