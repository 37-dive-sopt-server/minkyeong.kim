package org.sopt.domain.repository;

import java.util.List;
import org.sopt.domain.dto.projection.ArticleSearchProjection;

public interface ArticleRepositoryCustom {
    List<ArticleSearchProjection> search(String title, String memberName);
}
