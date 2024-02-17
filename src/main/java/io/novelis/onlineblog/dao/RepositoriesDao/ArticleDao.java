package io.novelis.onlineblog.dao.RepositoriesDao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.novelis.onlineblog.dao.repositories.ArticleRepository;
import io.novelis.onlineblog.domain.Article;
import io.novelis.onlineblog.domain.QArticle;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ArticleDao {
    private final ArticleRepository articleRepository;
    private final EntityManager entityManager;

    public Optional<Article> getById(Long id) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QArticle qArticle = QArticle.article;
        Article article = jpaQueryFactory.selectFrom(qArticle).where(qArticle.id.eq(id)).fetchOne();
        return Optional.ofNullable(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void save(Article article) {

        articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
