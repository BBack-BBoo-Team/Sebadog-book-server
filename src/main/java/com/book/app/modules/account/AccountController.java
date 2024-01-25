package com.book.app.modules.account;

import com.book.app.modules.account.dto.AccountInfoDto;
import com.book.app.modules.account.dto.SignUpDto;
import com.book.app.modules.account.service.AccountService;
import com.book.app.modules.global.response.SuccessResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    // 사용자 정보 저장
    @PostMapping("/sign-up")
    public ResponseEntity<SuccessResponseDto> signUp(@RequestBody @Valid SignUpDto info) {
        log.info("sign-up info = {}",info.toString());
        Account newAccount = accountService.saveSignUpInfo(info.toEntity());
        log.info("newAccount = {}", newAccount.toString());
        SuccessResponseDto<AccountInfoDto> result
                = new SuccessResponseDto<>("account", AccountInfoDto.from(newAccount));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
