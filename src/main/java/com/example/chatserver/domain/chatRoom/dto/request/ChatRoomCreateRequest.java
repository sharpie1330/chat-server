package com.example.chatserver.domain.chatRoom.dto.request;

import com.example.chatserver.domain.chatRoom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChatRoomCreateRequest {

    private Long memberId;
    private String name;

    public ChatRoom toEntity(Member member) {
        return ChatRoom.builder()
                .manager(member)
                .name(name)
                .build();
    }
}
