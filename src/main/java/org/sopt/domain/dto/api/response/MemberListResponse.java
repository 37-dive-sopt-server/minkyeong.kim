package org.sopt.domain.dto.api.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.sopt.domain.entity.Member;
import org.sopt.domain.enums.Gender;

public record MemberListResponse(
    List<MemberDto> members
) {
    public static MemberListResponse from(List<Member> memberList) {
        List<MemberDto> dtoList = memberList.stream()
            .map(MemberDto::from)
            .collect(Collectors.toList());

        return new MemberListResponse(dtoList);
    }

    public record MemberDto(
        String id,
        String name,
        LocalDate birthDate,
        String email,
        Gender gender
    ) {
        public static MemberDto from(Member member) {
            return new MemberDto(
                member.getId(),
                member.getName(),
                member.getBirthDate(),
                member.getEmail(),
                member.getGender()
            );
        }
    }
}