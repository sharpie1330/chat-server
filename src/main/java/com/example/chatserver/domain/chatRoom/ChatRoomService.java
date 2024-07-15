package com.example.chatserver.domain.chatRoom;

import com.example.chatserver.domain.chatRoom.dto.request.ChatRoomCreateRequest;
import com.example.chatserver.domain.chatRoom.dto.response.ChatRoomResponse;
import com.example.chatserver.domain.chatRoomMember.ChatRoomMember;
import com.example.chatserver.domain.chatRoomMember.ChatRoomMemberRepository;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.domain.member.MemberRepository;
import com.example.chatserver.domain.member.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Transactional
    public ChatRoomResponse save(ChatRoomCreateRequest request) {
        Member manager = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        ChatRoom chatRoom = request.toEntity(manager);
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        chatRoomMemberRepository.save(ChatRoomMember.builder()
                        .chatRoom(savedChatRoom)
                        .member(manager).build());

        return ChatRoomResponse.of(savedChatRoom);
    }

    @Transactional
    public List<MemberResponse> join(Long memberId, Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        ChatRoomMember chatRoomMember = new ChatRoomMember(chatRoom, member);

        if(chatRoom.isManager(memberId) || chatRoom.isMember(chatRoomMember)) {
            throw new IllegalArgumentException("이미 가입된 채팅방입니다.");
        }

        chatRoom.addMember(chatRoomMember);
        chatRoomMemberRepository.save(chatRoomMember);

        return chatRoom.getMembers().stream().map(crm -> MemberResponse.of(crm.getMember())).toList();
    }

    public List<ChatRoomResponse> getListByMemberId(Long memberId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByManagerId(memberId);
        return chatRooms.stream().map(ChatRoomResponse::of).toList();
    }

    public List<ChatRoomResponse> getList(Long memberId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream().map(ChatRoomResponse::of).toList();
    }
}
