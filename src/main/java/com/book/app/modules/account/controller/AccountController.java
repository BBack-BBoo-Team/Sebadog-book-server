package com.book.app.modules.account.controller;

import com.book.app.modules.account.Account;
import com.book.app.modules.account.dto.AccountInfoDto;
import com.book.app.modules.account.dto.SignUpDto;
import com.book.app.modules.account.service.AccountService;
import com.book.app.modules.global.Domain;
import com.book.app.modules.global.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    // 사용자 정보 저장
    @PostMapping("/sign-up")
    public ResponseEntity<Response> signUp(@RequestBody @Valid SignUpDto info) {
        Account newAccount = accountService.saveSignUpInfo(info.toEntity());
        return new ResponseEntity<>(
                Response.success(Domain.account, AccountInfoDto.from(newAccount)),
                HttpStatus.CREATED);
    }

    // 사용자 정보 조회
    @GetMapping("/info/{uid}")
    public ResponseEntity<Response> getInfoAccount(@PathVariable("uid") String uid) {
        Account getAccount = accountService.getAccountByUid(uid);
        return new ResponseEntity<>(
                Response.success(Domain.account, AccountInfoDto.from(getAccount)),
                HttpStatus.OK);
    }

}
