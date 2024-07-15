package com.example.chatserver.domain.chat;

import com.example.chatserver.domain.chat.dto.request.ChatMessageRequest;
import com.example.chatserver.domain.chat.dto.response.ChatMessageResponse;
import com.example.chatserver.domain.chatRoomMember.ChatRoomMember;
import com.example.chatserver.domain.chatRoomMember.ChatRoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Transactional
    public ChatMessageResponse save(Long chatRoomId, ChatMessageRequest request) {
        ChatRoomMember chatRoomMember = chatRoomMemberRepository.findByChatRoomIdAndMemberId(chatRoomId, request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방에 가입하지 않은 사용자입니다."));

        Chat chat = chatRepository.save(request.toEntity(chatRoomMember.getMember(), chatRoomMember.getChatRoom()));
        return ChatMessageResponse.of(chat);
    }

    public List<ChatMessageResponse> getChatMessages(Long memberId, Long chatRoomId) {
        chatRoomMemberRepository.findByChatRoomIdAndMemberId(chatRoomId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방에 가입하지 않은 사용자입니다."));

        return chatRepository.findAllByChatRoomId(chatRoomId).stream().map(ChatMessageResponse::of).toList();
    }
}
