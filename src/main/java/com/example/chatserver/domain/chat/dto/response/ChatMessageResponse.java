package com.example.chatserver.domain.chat.dto.response;

import com.example.chatserver.domain.chat.Chat;
import com.example.chatserver.global.type.ChatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMessageResponse {
    private Long chatId;
    private Long userId;
    private String userName;
    private ChatType chatType;
    private String message;
    private String createdAt;

    public static ChatMessageResponse of(Chat chat) {
        return ChatMessageResponse.builder()
                .chatId(chat.getId())
                .userId(chat.getMember().getId())
                .chatType(chat.getChatType())
                .message(chat.getContent())
                .createdAt(chat.getCreatedAt().toString())
                .build();
    }
}
