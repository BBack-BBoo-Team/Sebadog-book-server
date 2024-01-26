package com.book.app.modules.account.service;

import com.book.app.modules.account.Account;

/**
 * 사용자 CRUD에 해당하는 기능을 포함합니다.
 */
public interface AccountService {

    /**
     * 회원가입을 하는 사용자의 정보(uid, email, nickname)를 DB에 저장합니다.
     * 중복된 닉네임이 있는지 검증합니다.
     */
    Account saveSignUpInfo(Account account);

    /**
     * uid에 해당하는 사용자 정보 조회
     * @param uid
     * @return Account
     */
    Account getAccountByUid(String uid);
}
