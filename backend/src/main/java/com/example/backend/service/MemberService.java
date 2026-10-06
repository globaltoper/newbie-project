package com.example.backend.service;

import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.MemberRequestDto;
import com.example.backend.dto.MemberResponseDto;
import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto create(MemberRequestDto requestDto) {
        Member member = new Member(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return new MemberResponseDto(memberRepository.save(member));
    }

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDto::new)
                .toList();
    }

    public MemberResponseDto findById(Long id) {
        return new MemberResponseDto(findMember(id));
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto requestDto) {
        Member member = findMember(id);
        member.update(requestDto.getName(), requestDto.getEmail());
        return new MemberResponseDto(member);
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.delete(findMember(id));
    }

    public MemberResponseDto login(LoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .filter(m -> m.getPassword().equals(requestDto.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다."));
        return new MemberResponseDto(member);
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다. id=" + id));
    }
}
