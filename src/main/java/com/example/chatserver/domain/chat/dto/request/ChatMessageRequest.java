package com.example.chatserver.domain.chat.dto.request;

import com.example.chatserver.domain.chat.Chat;
import com.example.chatserver.domain.chatRoom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.global.type.ChatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatMessageRequest {

    @NotNull(message = "유저 아이디는 필수 값입니다.")
    private Long memberId;

    @NotBlank(message = "채팅 타입은 필수 값입니다.")
    private String chatType;

    @NotBlank(message = "메시지는 필수 값입니다.")
    private String message;

    public Chat toEntity(Member member, ChatRoom chatRoom) {
        return Chat.builder()
                .member(member)
                .chatRoom(chatRoom)
                .content(message)
                .chatType(ChatType.getChatType(chatType))
                .build();
    }
}
