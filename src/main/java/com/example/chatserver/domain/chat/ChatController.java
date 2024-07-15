package com.example.chatserver.domain.chat;

import com.example.chatserver.domain.chat.dto.request.ChatMessageRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;
    private final ChatService chatService;

    @MessageMapping("/chatRoom/{chatRoomId}")
    public void sendMessage(@DestinationVariable("chatRoomId") Long chatRoomId,
                            @Valid @RequestBody ChatMessageRequest request) {
        template.convertAndSend("/sub/chatRoom/" + chatRoomId, chatService.save(chatRoomId, request));
    }
}
