package org.sopt.domain.dto.api.response;

import java.time.LocalDate;
import org.sopt.domain.entity.Article;
import org.sopt.domain.enums.ArticleTag;

public record ArticleDetailResponse(
    String id,
    String memberId,
    ArticleTag tag,
    LocalDate articleDate,
    String title,
    String content
) {
    public static ArticleDetailResponse from(Article article) {
        return new ArticleDetailResponse(
            article.getId(),
            article.getMember().getId(),
            article.getTag(),
            article.getArticleDate(),
            article.getTitle(),
            article.getContent()
        );
    }
}
