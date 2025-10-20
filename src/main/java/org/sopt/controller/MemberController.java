package org.sopt.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.sopt.domain.Member;
import org.sopt.enums.Gender;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
     * 회원 등록 API
     */
    @PostMapping
    public Long createMember(String name, LocalDate birthDate, String email, Gender gender) {
        return memberService.join(name, birthDate, email, gender);
    }

    /*
     * 회원 삭제 API
     */
    @DeleteMapping("/{id}")
    public void deleteMember(Long id) {
        memberService.delete(id);
    }

    /*
     * 회원 단건 조회 API
     */
    @GetMapping("/{id}")
    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    /*
     * 회원 전체 조회 API
     */
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }
}
