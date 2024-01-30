package com.book.app.modules.account;

import com.book.app.infra.MockMvcTest;
import com.book.app.modules.account.dto.SignUpDto;
import com.book.app.modules.account.service.AccountService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
class AccountControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired Gson gson;

    @Autowired AccountService accountService;

    final String commonUrl = "/accounts";

    @DisplayName("회원가입 API 성공")
    @Test
    void signUp_success() throws Exception {

        SignUpDto request = new SignUpDto("test", "zzz@naver.com", "test");

        mockMvc.perform(post(commonUrl +"/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.domain").value("account"))
                .andExpect(jsonPath("$.data.uid").value(request.getUid()))
                .andExpect(jsonPath("$.data.email").value(request.getEmail()))
                .andExpect(jsonPath("$.data.nickname").value(request.getNickname()));
    }

    @Disabled
    @DisplayName("회원가입 API 실패 - 중복 닉네임")
    @Test
    void signUp_fail_duplicated_nickname() throws Exception {
        Account firstAccount = accountService.saveSignUpInfo(Account.of("first", "test@test.com", "test"));

        SignUpDto request = new SignUpDto("second", "test22@naver.com", firstAccount.getNickname());

        mockMvc.perform(post(commonUrl +"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException))
                .andExpect(result -> assertEquals("중복된 닉네임입니다.", result.getResolvedException().getMessage()));
    }

    @DisplayName("사용자 조회 API 성공")
    @Test
    void getInfoAccount_success() throws Exception {
        Account expectAccount = accountService.saveSignUpInfo(
                Account.of("first", "test@test.com", "test"));

        mockMvc.perform(get(commonUrl +"/info/{uid}",expectAccount.getUid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.domain").value("account"))
                .andExpect(jsonPath("$.data.uid").value(expectAccount.getUid()))
                .andExpect(jsonPath("$.data.email").value(expectAccount.getEmail()))
                .andExpect(jsonPath("$.data.nickname").value(expectAccount.getNickname()));
    }

    @Disabled
    @DisplayName("사용자 조회 API 실패 - 존재하지 않는 UID")
    @Test
    void getInfoAccount_fail_notFoundAccount_byUid() throws Exception {
        mockMvc.perform(get(commonUrl +"/info/{uid}","first"))
                .andExpect(status().isNotFound());
    }
}