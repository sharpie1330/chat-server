package com.example.chatserver.domain.chatRoom.dto.response;

import com.example.chatserver.domain.chatRoom.ChatRoom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoomResponse {

    private Long roomId;
    private Long managerId;
    private String name;

    public static ChatRoomResponse of(ChatRoom chatRoom) {
        return ChatRoomResponse.builder()
                .roomId(chatRoom.getId())
                .managerId(chatRoom.getId())
                .name(chatRoom.getName())
                .build();
    }
}
