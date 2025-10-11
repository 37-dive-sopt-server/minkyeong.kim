package org.sopt.controller;

import java.util.List;
import java.util.Optional;
import org.sopt.domain.Member;
import org.sopt.service.MemberService;

public class MemberController {

    private MemberService memberService;

    public Long createMember(String name) {

        return memberService.join(name);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }
}
