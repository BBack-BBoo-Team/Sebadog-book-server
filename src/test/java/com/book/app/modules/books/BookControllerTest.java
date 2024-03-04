package com.book.app.modules.books;

import com.book.app.infra.MockMvcTest;
import com.book.app.modules.books.dto.SaveBook;
import com.book.app.modules.books.dto.SaveBookInfo;
import com.book.app.modules.books.service.BookService;
import com.book.app.modules.global.exception.ErrorCode.BookErrorCode;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        SaveBook request = new SaveBook("책 제목", "저자", "출판사","이미지","진행예정", "작성자");

        mockMvc.perform(post(commonUrl +"/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.title").value(request.getTitle()))
                .andExpect(jsonPath("$.data.author").value(request.getAuthor()))
                .andExpect(jsonPath("$.data.img").value(request.getImg()))
                .andExpect(jsonPath("$.data.publisher").value(request.getPublisher()));
//                .andExpect(jsonPath("$.data.status").value(BookStatus.fromString(request.getStatus())));
    }

    @DisplayName("[도서 등록] 실패 - 존재하지 않는 도서 진행 상태 입력")
    @Test
    void addBooks_fail_badRequest_status() throws Exception {

        SaveBook request = new SaveBook("책 제목", "저자", "출판사","이미지","진행할거", "작성자");

        mockMvc.perform(post(commonUrl +"/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.errors.name").value(BookErrorCode.STATUS_BAD_REQUEST.name())) // 예상되는 오류 코드
                .andExpect(jsonPath("$.errors.message").value(BookErrorCode.STATUS_BAD_REQUEST.getMessage())); // 예상되는 오류 메시지
    }


    @DisplayName("[도서 상세 조회] 성공")
    @Test
    void getBookDetail_success() throws Exception {
        SaveBook request = new SaveBook("책 제목", "저자", "출판사","이미지","진행예정", "작성자");
        SaveBookInfo response = bookService.addBookInfo(request);

        mockMvc.perform(get(commonUrl + "/details/{id}", response.getBookId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.title").value(response.getTitle()))
                .andExpect(jsonPath("$.data.author").value(response.getAuthor()))
                .andExpect(jsonPath("$.data.img").value(response.getImg()))
                .andExpect(jsonPath("$.data.publisher").value(response.getPublisher()))
                .andExpect(jsonPath("$.data.status").value(String.valueOf(response.getStatus())))
                .andExpect(jsonPath("$.data.created_by").value(response.getCreatedBy()));
    }

    @DisplayName("[도서 상세 조회] 실패 - 존재하지 않는 도서 ID")
    @Test
    void getBookDetail_fail_notFound() throws Exception {
        mockMvc.perform(get(commonUrl + "/details/{id}", "1"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.errors.name").value(BookErrorCode.BOOK_ID_NOT_FOUND.name()))
                .andExpect(jsonPath("$.errors.message").value(BookErrorCode.BOOK_ID_NOT_FOUND.getMessage()));
    }
}
