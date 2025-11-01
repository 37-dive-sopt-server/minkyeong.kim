package org.sopt.domain.controller;

import jakarta.validation.Valid;
import org.sopt.domain.dto.api.request.MemberCreateRequest;
import org.sopt.domain.dto.api.response.MemberDetailResponse;
import org.sopt.domain.dto.api.response.MemberListResponse;
import org.sopt.global.common.dto.ApiResponse;
import org.sopt.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
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
    public ResponseEntity<ApiResponse<Void>> createMember(@Valid @RequestBody MemberCreateRequest request) {
        memberService.createMember(request.toCommand());

        return ApiResponse.success();
    }

    /*
     * 회원 삭제 API
     */
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Long memberId) {
        memberService.deleteById(memberId);

        return ApiResponse.success();
    }

    /*
     * 회원 단건 조회 API
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberDetailResponse>> getMemberById(@PathVariable Long memberId) {
        return ApiResponse.success(memberService.getMemberById(memberId));
    }

    /*
     * 회원 전체 조회 API
     */
    @GetMapping
    public ResponseEntity<ApiResponse<MemberListResponse>> getAllMembers() {
        return ApiResponse.success(memberService.getAllMembers());
    }
}