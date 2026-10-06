package com.example.backend.controller;

import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.MemberRequestDto;
import com.example.backend.dto.MemberResponseDto;
import com.example.backend.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping //생성
    public ResponseEntity<MemberResponseDto> create(@Valid @RequestBody MemberRequestDto requestDto) {
        System.out.println("MemberController.create called with requestDto: " + requestDto[0]);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.create(requestDto));
    }

    @GetMapping //전체조회
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}") //조회
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/{id}") //수정
    public ResponseEntity<MemberResponseDto> update(@PathVariable Long id, @Valid @RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(memberService.update(id, requestDto));
    }

    @DeleteMapping("/{id}") //삭제
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login") //로그인
    public ResponseEntity<MemberResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(memberService.login(requestDto));
    }
}
