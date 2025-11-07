package org.sopt.domain.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.sopt.domain.dto.service.command.ArticleCreateCommand;
import org.sopt.domain.enums.ArticleTag;
import org.sopt.global.common.entity.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    indexes = {
        @Index(name = "idx_article_member_id", columnList = "member_id"),
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_article_title", columnNames = "title")
    }
)
@SQLRestriction("deleted_at is null")
@SQLDelete(sql = "UPDATE article SET deleted_at = now() WHERE id = ?")
@Comment("아티클 정보")
public class Article extends BaseEntity {

    @Id
    @Tsid
    @Column(length = 13)
    @Comment("아티클 PK")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Comment("태그/분야")
    private ArticleTag tag;

    @Column(name = "article_date", nullable = false)
    @Comment("작성일")
    private LocalDate articleDate;

    @Column(nullable = false, length = 200)
    @Comment("제목")
    private String title;

    @Column(nullable = false)
    @Comment("내용")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("작성자 회원 PK")
    private Member member;

    @Builder
    private Article(ArticleTag tag, LocalDate articleDate, String title, String content, Member member) {
        this.tag = tag;
        this.articleDate = articleDate;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public static Article create(ArticleCreateCommand command, Member member) {
        return Article.builder()
            .tag(command.tag())
            .articleDate(command.articleDate())
            .title(command.title())
            .content(command.content())
            .member(member)
            .build();
    }
}
