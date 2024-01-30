package com.book.app.modules.account;

import com.book.app.infra.MockMvcTest;
import com.book.app.modules.account.dto.SignUpDto;
import com.book.app.modules.account.service.AccountService;
import com.book.app.modules.global.exception.ErrorCode.AccountErrorCode;
import com.book.app.modules.global.exception.ErrorCode.CommonErrorCode;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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


    @DisplayName("[회원가입] 성공")
    @Test
    void signUp_success() throws Exception {

        SignUpDto request = new SignUpDto("test", "test@test.com", "test");

        mockMvc.perform(post(commonUrl +"/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.uid").value(request.getUid()))
                .andExpect(jsonPath("$.data.email").value(request.getEmail()))
                .andExpect(jsonPath("$.data.nickname").value(request.getNickname()));
    }

    @DisplayName("[회원가입] 실패 - 중복 닉네임")
    @Test
    void signUp_fail_duplicated_nickname() throws Exception {
        Account firstAccount = accountService.saveSignUpInfo(Account.of("first", "first@email.com", "first"));
        SignUpDto request = new SignUpDto("second", "second@email.com", firstAccount.getNickname());

        mockMvc.perform(post(commonUrl +"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.errors.name").value(AccountErrorCode.DUPLICATED_NICKNAME.name()))
                .andExpect(jsonPath("$.errors.message").value(AccountErrorCode.DUPLICATED_NICKNAME.getMessage()));
    }

    @DisplayName("[회원가입] 성공 - 닉네임은 2자 이상 20자 이하, 한글, 영어(대소), 숫자는 유효성 검증 성공")
    @ParameterizedTest
    @ValueSource(strings = {"2자", "20자되는닉네임입니다룰루랄라룰루랄라룰", "테스트", "hong", "이nyeosuk"})
    void signUp_success_validate_nickname(String nickname) throws Exception {

        SignUpDto request = new SignUpDto("uid", "test@email.com", nickname);

        mockMvc.perform(post(commonUrl +"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.uid").value(request.getUid()))
                .andExpect(jsonPath("$.data.email").value(request.getEmail()))
                .andExpect(jsonPath("$.data.nickname").value(request.getNickname()));
    }

    @DisplayName("[회원가입] 실패 - 닉네임은 2자 미만 20자 초과, 특수문자는 유효성 검증에서 실패")
    @ParameterizedTest
    @ValueSource(strings = {"1", "21자되는닉네임입니다룰루랄라룰루랄라룰루", "+!-@#"})
    void signUp_fail_validate_nickname(String nickname) throws Exception {

        SignUpDto request = new SignUpDto("uid", "test@email.com", nickname);

        mockMvc.perform(post(commonUrl +"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.errors.name").value(CommonErrorCode.INVALID_VALIDATION.name()))
                .andExpect(jsonPath("$.errors.message").value(CommonErrorCode.INVALID_VALIDATION.getMessage()));
    }

    @DisplayName("[사용자 조회] 성공")
    @Test
    void getInfoAccount_success() throws Exception {
        Account expectAccount = accountService.saveSignUpInfo(
                Account.of("first", "test@test.com", "test"));

        mockMvc.perform(get(commonUrl +"/info/{uid}",expectAccount.getUid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.uid").value(expectAccount.getUid()))
                .andExpect(jsonPath("$.data.email").value(expectAccount.getEmail()))
                .andExpect(jsonPath("$.data.nickname").value(expectAccount.getNickname()));
    }

    @DisplayName("[사용자 조회] 실패 - 존재하지 않는 UID")
    @Test
    void getInfoAccount_fail_notFoundAccount_byUid() throws Exception {
        mockMvc.perform(get(commonUrl +"/info/{uid}","first"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.errors.name").value(AccountErrorCode.UID_NOT_FOUND.name()))
                .andExpect(jsonPath("$.errors.message").value(AccountErrorCode.UID_NOT_FOUND.getMessage()));
    }
}