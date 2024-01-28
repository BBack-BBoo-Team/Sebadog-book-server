package com.book.app.modules.account.dto;

import com.book.app.modules.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 단일 사용자 데이터
 * @uid : Supabase에서 제공하는 사용자 식별자
 * @email : 로그인을 위한 이메일
 * @nickname : 사용자 별칭
 * @joinAt : DB 저장 날짜 및 시간
 * @profileImg : 프로필 이미지. null 이라면, length 0인 문자열 "" 반환
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountInfoDto {

    private String uid;
    private String email;
    private String nickname;

    @JsonProperty("join_at")
    private LocalDateTime joinAt;

    @JsonProperty("profile_img")
    private String profileImg;

    public static AccountInfoDto from(Account account) {
        return AccountInfoDto.builder()
                .uid(account.getUid())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .joinAt(account.getJoinAt())
                .profileImg(account.getProfileImg())
                .build();
    }
}
