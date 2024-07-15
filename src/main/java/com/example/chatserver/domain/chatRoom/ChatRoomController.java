package com.example.chatserver.domain.chatRoom;

import com.example.chatserver.domain.chatRoom.dto.request.ChatRoomCreateRequest;
import com.example.chatserver.domain.chatRoom.dto.response.ChatRoomResponse;
import com.example.chatserver.domain.member.dto.response.MemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatRooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoomResponse> save(@Valid @RequestBody ChatRoomCreateRequest request) {
        return ResponseEntity.ok(chatRoomService.save(request));
    }

    @PostMapping("/{chatRoomId}")
    public ResponseEntity<List<MemberResponse>> join(@RequestParam("memberId") Long memberId, @PathVariable("chatRoomId") Long chatRoomId) {
        return ResponseEntity.ok(chatRoomService.join(memberId, chatRoomId));
    }
}
