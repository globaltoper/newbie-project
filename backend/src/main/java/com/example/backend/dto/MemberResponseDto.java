package com.example.backend.dto;

import com.example.backend.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.createdAt = member.getCreatedAt();
    }
}
