package org.sopt.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import org.sopt.domain.Member;
import org.sopt.enums.Gender;
import org.sopt.exception.BusinessException;
import org.sopt.exception.ErrorCode;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.utils.IdGenerator;

public class MemberService {
    private final MemoryMemberRepository memberRepository;

    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(String name, LocalDate birthDate, String email, Gender gender) {
        validateDuplicateEmail(email);
        validateAge(birthDate);

        Member member = Member.create(IdGenerator.next(), name, birthDate, email, gender);
        memberRepository.save(member);

        return member.getId();
    }

    public void delete(Long memberId) {
        boolean removedMember = memberRepository.deleteById(memberId);

        if (!removedMember) {
            throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    private void validateDuplicateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new BusinessException(ErrorCode.EMAIL_BLANK);
        }
        if (memberRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.MEMBER_BY_EMAIL_ALREADY_EXISTS);
        }
    }

    private void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new BusinessException(ErrorCode.INVALID_DATE_FORMAT);
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 20) {
            throw new BusinessException(ErrorCode.AGE_MUST_UPPER_THAN_20);
        }
    }
}