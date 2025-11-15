package org.sopt.domain.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.dto.api.request.ArticleCreateRequest;
import org.sopt.domain.dto.api.response.ArticleDetailResponse;
import org.sopt.domain.dto.api.response.ArticleListResponse;
import org.sopt.domain.dto.api.response.ArticleSearchResponse;
import org.sopt.domain.service.ArticleService;
import org.sopt.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 아티클 생성 API
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
        @RequestHeader("userId") String userId,
        @Valid @RequestBody ArticleCreateRequest request
    ) {
        articleService.createArticle(request.toCommand(userId));

        return ApiResponse.success();
    }

    /**
     * 아티클 단건 조회 API
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> getArticle(@PathVariable String articleId) {
        return ApiResponse.success(articleService.getArticleById(articleId));
    }

    /**
     * 아티클 전체 조회 API
     * (작성자 이름 포함, id 내림차순)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<ArticleListResponse>> getAllArticles() {
        return ApiResponse.success(articleService.getAllArticles());
    }

    /**
     * 검색 API
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<ArticleSearchResponse>> searchArticles(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String memberName
    ) {
        return ApiResponse.success(articleService.searchArticles(title, memberName));
    }
}