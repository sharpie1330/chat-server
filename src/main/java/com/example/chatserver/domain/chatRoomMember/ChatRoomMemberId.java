package com.example.chatserver.domain.chatRoomMember;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ChatRoomMemberId implements Serializable {

    @Column(nullable = false)
    private Long chatRoomId;

    @Column(nullable = false)
    private Long memberId;

    public ChatRoomMemberId(Long chatRoomId, Long memberId) {
        this.chatRoomId = chatRoomId;
        this.memberId = memberId;
    }
}
