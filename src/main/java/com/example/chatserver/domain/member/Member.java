package com.example.chatserver.domain.member;

import com.example.chatserver.domain.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String encodedPwd;

    @Column(nullable = false)
    private String nickname;

    @Builder
    public Member(String loginId, String encodedPwd, String nickname) {
        this.loginId = loginId;
        this.encodedPwd = encodedPwd;
        this.nickname = nickname;
    }

    public boolean isMember(Long memberId) {
        return memberId.equals(this.id);
    }
}
