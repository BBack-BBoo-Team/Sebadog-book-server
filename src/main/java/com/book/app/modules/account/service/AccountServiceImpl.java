package com.book.app.modules.account.service;

import com.book.app.modules.account.Account;
import com.book.app.modules.account.AccountRepository;
import com.book.app.modules.global.exception.BusinessLogicException;
import com.book.app.modules.global.exception.ErrorCode.AccountErrorCode;
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

    @Override
    public Account getAccountByUid(String uid) {
        return accountRepository.findByUid(uid)
                .orElseThrow(() -> new BusinessLogicException(AccountErrorCode.UID_NOT_FOUND));
    }

    private void verifyExistsNickname(Account account) {
        accountRepository.findByNickname(account.getNickname()).ifPresent(e -> {
            throw new BusinessLogicException(AccountErrorCode.DUPLICATED_NICKNAME);
        });
    }
}
