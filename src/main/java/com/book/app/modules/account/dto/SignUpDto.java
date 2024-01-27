package com.book.app.modules.account.dto;

import com.book.app.modules.account.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * 회원가입 데이터
 */
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @NotBlank(message = "uid를 입력해주세요.")
    private String uid;

    @Email(message = "이메일 형식에 맞춰주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    /**
     * 2~20자 길이의 한글, 영어(대소문자), 숫자(0-9) 만 허용
     */
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Length(min = 2, max = 20, message = "닉네임 길이는 2자 이상 20자 이하로 작성해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣A-Za-z0-9]{2,20}$", message = "닉네임은 한글, 영어(대소문자), 숫자만 허용하며 2자에서 20자 사이어야 합니다.")
    private String nickname;

    public Account toEntity() {
        return Account.of(this.uid, this.email, this.nickname);
    }
}
