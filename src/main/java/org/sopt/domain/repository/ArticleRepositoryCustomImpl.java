package org.sopt.domain.repository;

import static org.sopt.domain.entity.QArticle.article;
import static org.sopt.domain.entity.QMember.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.dto.projection.ArticleSearchProjection;
import org.sopt.domain.dto.projection.QArticleSearchProjection;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<ArticleSearchProjection> search(String title, String memberName) {
        BooleanBuilder whereCondition = new BooleanBuilder();

        if (StringUtils.hasText(title)) {
            whereCondition.and(article.title.containsIgnoreCase(title));
        }
        if (StringUtils.hasText(memberName)) {
            whereCondition.and(member.name.containsIgnoreCase(memberName));
        }

        return query.select(new QArticleSearchProjection(
                article.id,
                member.id,
                member.name,
                article.tag,
                article.articleDate,
                article.title
            ))
            .from(article)
            .join(article.member, member)
            .where(whereCondition)
            .orderBy(article.id.desc())
            .fetch();
    }
}