package io.novelis.onlineblog.dao.repositories;

import io.novelis.onlineblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // You can add custom query methods here if needed
}
