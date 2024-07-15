package com.example.chatserver.domain.member;

import com.example.chatserver.domain.member.dto.request.MemberCreateRequest;
import com.example.chatserver.domain.member.dto.response.MemberResponse;
import com.example.chatserver.global.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponse save(MemberCreateRequest request) {
        Member member = request.toEntity(passwordEncoder.encode(request.getPassword()));
        Member savedMember = memberRepository.save(member);

        return MemberResponse.of(savedMember);
    }
}
