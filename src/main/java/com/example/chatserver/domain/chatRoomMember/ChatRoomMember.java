package com.example.chatserver.domain.chatRoomMember;

import com.example.chatserver.domain.chatRoom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ChatRoomMember implements Persistable<ChatRoomMemberId> {

    @EmbeddedId
    private ChatRoomMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "chatRoomId")
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "memberId")
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public ChatRoomMember(ChatRoom chatRoom, Member member) {
        this.chatRoom = chatRoom;
        this.member = member;
        this.id = new ChatRoomMemberId(chatRoom.getId(), member.getId());
    }

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
