package org.sopt.domain.dto.projection;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.Getter;
import org.sopt.domain.enums.ArticleTag;

@Getter
public class ArticleSearchProjection {
    private final String id;
    private final String memberId;
    private final String memberName;
    private final ArticleTag tag;
    private final LocalDate articleDate;
    private final String title;

    @QueryProjection
    public ArticleSearchProjection(
        String id,
        String memberId,
        String memberName,
        ArticleTag tag,
        LocalDate articleDate,
        String title
    ) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.tag = tag;
        this.articleDate = articleDate;
        this.title = title;
    }
}
