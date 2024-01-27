package com.book.app.modules.account.service;

import com.book.app.modules.account.Account;
import com.book.app.modules.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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

    @Override
    public Account getAccountByUid(String uid) {
        return accountRepository.findByUid(uid).orElseThrow(() -> new NoSuchElementException("uid에 해당하는 사용자를 찾을 수 없습니다."));
    }

    private void verifyExistsNickname(Account account) {
        accountRepository.findByNickname(account.getNickname()).ifPresent(e -> {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        });
    }
}
