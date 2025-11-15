package org.sopt.domain.dto.api.response;

import java.time.LocalDate;
import org.sopt.domain.entity.Member;
import org.sopt.domain.enums.Gender;

public record MemberDetailResponse(
    String id,
    String name,
    LocalDate birthDate,
    String email,
    Gender gender
) {
    public static MemberDetailResponse from(Member member) {
        return new MemberDetailResponse(
            member.getId(),
            member.getName(),
            member.getBirthDate(),
            member.getEmail(),
            member.getGender()
        );
    }
}