package com.example.chatserver.domain.member.dto.request;

import com.example.chatserver.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberCreateRequest {

    private String nickname;
    private String loginId;
    private String password;

    public Member toEntity(String encodedPwd) {
        return Member.builder()
                .loginId(loginId)
                .nickname(nickname)
                .encodedPwd(encodedPwd)
                .build();
    }
}
