package com.example.chatserver.domain.chat;

import com.example.chatserver.domain.chatRoom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.global.type.ChatType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatType chatType;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Chat(ChatRoom chatRoom, Member member, ChatType chatType, String content) {
        this.chatRoom = chatRoom;
        this.member = member;
        this.chatType = chatType;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
