package org.sopt.domain.dto.api.response;

import java.time.LocalDate;
import java.util.List;
import org.sopt.domain.dto.projection.ArticleSearchProjection;
import org.sopt.domain.enums.ArticleTag;

public record ArticleSearchResponse(
    List<Item> items
) {
    public static ArticleSearchResponse fromProjection(List<ArticleSearchProjection> projections) {
        return new ArticleSearchResponse(
            projections.stream()
                .map(p -> new Item(
                    p.getId(),
                    p.getMemberId(),
                    p.getMemberName(),
                    p.getTag(),
                    p.getArticleDate(),
                    p.getTitle()
                ))
                .toList()
        );
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