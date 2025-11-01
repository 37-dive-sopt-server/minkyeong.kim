package org.sopt.domain.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.sopt.domain.entity.Member;
import org.sopt.domain.dto.api.response.MemberDetailResponse;
import org.sopt.domain.dto.api.response.MemberListResponse;
import org.sopt.domain.dto.service.command.MemberCreateCommand;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode;
import org.sopt.domain.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemoryMemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(MemberCreateCommand command) {
        validateDuplicateEmail(command.email());
        validateAge(command.birthDate());

        Member member = Member.create(command);
        memberRepository.save(member);
    }

    public void deleteById(Long memberId) {
        boolean removedMember = memberRepository.deleteById(memberId);

        if (!removedMember) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }
    }

    public MemberDetailResponse getMemberById(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberDetailResponse.from(member);
    }

    public MemberListResponse getAllMembers() {
        List<Member> members = memberRepository.findAll();

        return MemberListResponse.from(members);
    }

    private void validateDuplicateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new CustomException(ErrorCode.EMAIL_BLANK);
        }
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.MEMBER_BY_EMAIL_ALREADY_EXISTS);
        }
    }

    private void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new CustomException(ErrorCode.INVALID_DATE_FORMAT);
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 20) {
            throw new CustomException(ErrorCode.AGE_MUST_UPPER_THAN_20);
        }
    }
}