package com.book.app.modules.account.service;

import com.book.app.modules.account.Account;
import com.book.app.modules.account.AccountRepository;
import com.book.app.modules.account.dto.SignUpInfo;
import com.book.app.modules.global.exception.BusinessLogicException;
import com.book.app.modules.global.exception.ErrorCode.AccountErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock private AccountRepository accountRepository;
    @InjectMocks private AccountServiceImpl accountService;

    @Test
    void 회원가입중_중복닉네임의_경우_BusinessLogicException_발생() {
        // 가정
        Account alreadyExistAccount = Account.of("test","test@test.com","test");
        given(accountRepository.findByNickname(anyString())).willReturn(Optional.of(alreadyExistAccount));

        SignUpInfo secondSignUpInfo = new SignUpInfo("test2", "test2@test.com", alreadyExistAccount.getNickname());

        // 1) BusinessLogicException 발생 여부 체
        assertThatThrownBy(()->
                accountService.saveSignUpInfo(secondSignUpInfo))
                .isInstanceOf(BusinessLogicException.class);

        // 2) 발생한 예외 상세 정보 적합성 체크
        BusinessLogicException expected = assertThrows(BusinessLogicException.class, () -> {
            accountService.saveSignUpInfo(secondSignUpInfo);
        });
        assertEquals(AccountErrorCode.DUPLICATED_NICKNAME, expected.getErrorCode());
    }

    @ParameterizedTest
    @ValueSource(strings = "Non-existent-UID")
    void 존재하지_않는_UID로_회원_조회시_BusinessLogicException_발생(String requestUid) {
        assertThatThrownBy(()->
                accountService.getAccountByUid(requestUid))
                .isInstanceOf(BusinessLogicException.class);

        BusinessLogicException expected = assertThrows(BusinessLogicException.class, () -> {
            accountService.getAccountByUid(requestUid);
        });
        assertEquals(AccountErrorCode.UID_NOT_FOUND, expected.getErrorCode());
    }
}