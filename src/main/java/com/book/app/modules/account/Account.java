package com.book.app.modules.account;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ACCOUNT")
@Entity
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UID", unique = true, updatable = false, nullable = false)
    private String uid; // Supabase 제공

    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "NICKNAME", unique = true, nullable = false, length = 20)
    private String nickname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(name = "JOIN_AT", nullable = false, updatable = false)
    private LocalDateTime joinAt;

    @Column(name = "PROFILE_IMG")
    private String profileImg;

    private Account(String uid, String email, String nickname) {
        this.uid = uid;
        this.email = email;
        this.nickname = nickname;
    }

    public static Account of(String uid, String email, String nickname) {
        return new Account(uid, email, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return id != null && id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

