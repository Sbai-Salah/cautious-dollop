package io.novelis.onlineblog.dao.repositories;

import io.novelis.onlineblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
}
