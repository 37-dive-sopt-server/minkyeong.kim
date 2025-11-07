package org.sopt.domain.dto.api.response;

import java.time.LocalDate;
import java.util.List;
import org.sopt.domain.entity.Article;
import org.sopt.domain.enums.ArticleTag;


public record ArticleListResponse(
    List<Item> items
) {
    public static ArticleListResponse from(List<Article> articles) {
        List<Item> mapped = articles.stream()
            .map(a -> new Item(
                a.getId(),
                a.getMember().getId(),
                a.getMember().getName(),
                a.getTag(),
                a.getArticleDate(),
                a.getTitle()
            ))
            .toList();
        return new ArticleListResponse(mapped);
    }

    public record Item(
        String id,
        String memberId,
        String memberName,
        ArticleTag tag,
        LocalDate articleDate,
        String title
    ) {}
}
