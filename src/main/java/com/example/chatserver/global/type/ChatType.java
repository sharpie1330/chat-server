package com.example.chatserver.global.type;

import java.util.Arrays;

public enum ChatType {
    JOIN, CHAT, EXIT, FORCE_OUT;

    public static ChatType getChatType(String chatType) {
        return Arrays.stream(ChatType.values())
                .filter(cType -> cType.name().equalsIgnoreCase(chatType))
                .findAny().orElseThrow(() -> new IllegalArgumentException("해당 채팅 타입이 존재하지 않습니다."));
    }
}
