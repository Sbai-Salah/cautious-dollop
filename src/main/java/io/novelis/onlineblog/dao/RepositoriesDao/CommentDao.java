package io.novelis.onlineblog.dao.RepositoriesDao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.novelis.onlineblog.dao.repositories.CommentRepository;
import io.novelis.onlineblog.dao.repositories.UserRepository;
import io.novelis.onlineblog.domain.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class CommentDao {


    private final CommentRepository commentRepository;
    private final EntityManager entityManager;

//    public Optional<Comment> getById(Long id) {
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
//        QComment qComment = QComment.comment;
//        Comment comment = jpaQueryFactory.selectFrom(qComment).where(qComment.id.eq(id)).fetchOne();
//        return Optional.ofNullable(comment);
//    }

    public Optional<Comment> getById(Long id) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QComment qComment = QComment.comment;
        QUser qUser = QUser.user;
        QArticle qArticle = QArticle.article;

        Comment comment = jpaQueryFactory
                .selectFrom(qComment)
                .leftJoin(qComment.user, qUser).fetchJoin() // Fetch the associated user
                .leftJoin(qComment.article, qArticle).fetchJoin() // Fetch the associated article
                .where(qComment.id.eq(id))
                .fetchFirst();
        System.out.println("comment id :" + comment.getId());
        System.out.println("User id :" + comment.getUser().getId());
        System.out.println("article id :" + comment.getArticle().getId());
        return Optional.of(comment);
    }


    public List<Comment> findByArticleId(Long articleId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QComment qComment = QComment.comment;

        return queryFactory.selectFrom(qComment)
                .where(qComment.article.id.eq(articleId))
                .fetch();
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
    // Add more methods as needed
}
