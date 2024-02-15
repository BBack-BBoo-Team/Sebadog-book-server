package com.book.app.modules.account.service;

import com.book.app.modules.account.Account;
import com.book.app.modules.account.AccountRepository;
import com.book.app.modules.account.dto.AccountInfo;
import com.book.app.modules.account.dto.SignUpInfo;
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
    public AccountInfo saveSignUpInfo(SignUpInfo signUpInfo) {
        Account newAccount = signUpInfo.toEntity();
        verifyExistsNickname(newAccount);
        return AccountInfo.from(accountRepository.save(newAccount));
    }

    @Override
    public AccountInfo getAccountByUid(String uid) {
        return accountRepository.findByUid(uid)
                .map(AccountInfo::from)
                .orElseThrow(() -> new BusinessLogicException(AccountErrorCode.UID_NOT_FOUND));
    }

    private void verifyExistsNickname(Account account) {
        accountRepository.findByNickname(account.getNickname()).ifPresent(e -> {
            throw new BusinessLogicException(AccountErrorCode.DUPLICATED_NICKNAME);
        });
    }
}
