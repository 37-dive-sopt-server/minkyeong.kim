package org.sopt.domain.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.dto.api.response.ArticleDetailResponse;
import org.sopt.domain.dto.api.response.ArticleListResponse;
import org.sopt.domain.dto.api.response.ArticleSearchResponse;
import org.sopt.domain.dto.service.command.ArticleCreateCommand;
import org.sopt.domain.entity.Article;
import org.sopt.domain.entity.Member;
import org.sopt.domain.repository.ArticleRepository;
import org.sopt.domain.repository.MemberRepository;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createArticle(ArticleCreateCommand command) {
        if (articleRepository.existsByTitle(command.title())) {
            throw new CustomException(ErrorCode.ARTICLE_TITLE_DUPLICATED);
        }

        Member member = memberRepository.findById(command.memberId())
            .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Article article = Article.create(command, member);

        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public ArticleDetailResponse getArticleById(String articleId) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));

        return ArticleDetailResponse.from(article);
    }

    @Transactional(readOnly = true)
    public ArticleListResponse getAllArticles() {
        List<Article> list = articleRepository.findAllWithMemberOrderByIdDesc();

        return ArticleListResponse.from(list);
    }

    @Transactional(readOnly = true)
    public ArticleSearchResponse searchArticles(String title, String memberName) {
        return ArticleSearchResponse.fromProjection(articleRepository.search(title, memberName));
    }
}