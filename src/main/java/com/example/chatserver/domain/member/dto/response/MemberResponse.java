package com.example.chatserver.domain.member.dto.response;

import com.example.chatserver.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private Long id;
    private String nickname;
    private String loginId;

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .loginId(member.getLoginId())
                .build();
    }
}
