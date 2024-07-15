package com.example.chatserver.domain.chatRoom;

import com.example.chatserver.domain.BaseTime;
import com.example.chatserver.domain.chatRoomMember.ChatRoomMember;
import com.example.chatserver.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "member_id", nullable = false)
    private Member manager;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Set<ChatRoomMember> members;

    @Builder
    public ChatRoom(Member manager, String name) {
        this.manager = manager;
        this.name = name;
        this.members = new HashSet<>();
    }

    public void addMember(ChatRoomMember chatRoomMember){
        members.add(chatRoomMember);
    }

    public boolean isManager(Long memberId) {
        return manager.isMember(memberId);
    }

    public boolean isMember(ChatRoomMember chatRoomMember) {
        return members.contains(chatRoomMember);
    }
}
