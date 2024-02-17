package io.novelis.onlineblog.dao.repositories;

import io.novelis.onlineblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // You can add custom query methods here if needed
}
