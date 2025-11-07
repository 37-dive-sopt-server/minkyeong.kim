package org.sopt.domain.repository;

import java.util.List;
import org.sopt.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, String>, ArticleRepositoryCustom {

    @Query("""
           select a
           from Article a
           join fetch a.member m
           order by a.id desc
           """)
    List<Article> findAllWithMemberOrderByIdDesc();

    boolean existsByTitle(String title);
}
