package io.novelis.onlineblog.dao.RepositoriesDao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.novelis.onlineblog.dao.repositories.UserRepository;
import io.novelis.onlineblog.domain.QUser;
import io.novelis.onlineblog.domain.User;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDao {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public Optional<User> getById(Long id) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QUser qUser = QUser.user;
        User user = jpaQueryFactory.selectFrom(qUser).where(qUser.id.eq(id)).fetchOne();
        return Optional.ofNullable(user);
    }
    public List<User> findAll() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QUser qUser = QUser.user;
        return jpaQueryFactory.selectFrom(qUser).fetch();
    }

    public void save(User user) {
        userRepository.save(user);
    }
    // Add more methods as needed
}