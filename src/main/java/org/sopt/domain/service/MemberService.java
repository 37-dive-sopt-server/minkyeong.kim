package org.sopt.domain.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.dto.api.response.MemberDetailResponse;
import org.sopt.domain.dto.api.response.MemberListResponse;
import org.sopt.domain.dto.service.command.MemberCreateCommand;
import org.sopt.domain.entity.Member;
import org.sopt.domain.repository.MemberRepository;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberCreateCommand command) {
        command.validateAge();

        if (memberRepository.existsByEmail(command.email().trim())) {
            throw new CustomException(ErrorCode.MEMBER_BY_EMAIL_ALREADY_EXISTS);
        }

        memberRepository.save(Member.create(command));
    }

    @Transactional
    public void deleteById(String memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        memberRepository.deleteById(memberId);
    }

    @Transactional(readOnly = true)
    public MemberDetailResponse getMemberById(String id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberDetailResponse.from(member);
    }

    @Transactional(readOnly = true)
    public MemberListResponse getAllMembers() {
        List<Member> members = memberRepository.findAll();

        return MemberListResponse.from(members);
    }
}