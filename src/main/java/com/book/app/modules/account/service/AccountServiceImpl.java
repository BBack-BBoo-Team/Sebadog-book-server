package com.book.app.modules.account.service;

import com.book.app.modules.account.Account;
import com.book.app.modules.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account saveSignUpInfo(Account account) {
        verifyExistsNickname(account);
        return accountRepository.save(account);
    }

    private void verifyExistsNickname(Account account) {
        accountRepository.findByNickname(account.getNickname()).ifPresent(e -> {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        });
    }
}
