package com.example.chatserver.domain.member;

import com.example.chatserver.domain.member.dto.request.MemberCreateRequest;
import com.example.chatserver.domain.member.dto.response.MemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> save(@Valid @RequestBody MemberCreateRequest request) {
        return ResponseEntity.ok(memberService.save(request));
    }
}
