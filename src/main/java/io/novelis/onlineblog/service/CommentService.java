package io.novelis.onlineblog.service;

import io.novelis.onlineblog.api.resource.ArticleResource;
import io.novelis.onlineblog.dao.RepositoriesDao.CommentDao;
import io.novelis.onlineblog.dao.repositories.ArticleRepository;
import io.novelis.onlineblog.dao.repositories.UserRepository;
import io.novelis.onlineblog.domain.Comment;
import io.novelis.onlineblog.service.dto.ArticleDto;
import io.novelis.onlineblog.service.dto.CommentDto;
import io.novelis.onlineblog.service.exception.BusinessException;
import io.novelis.onlineblog.service.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    private final CommentMapper commentMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public CommentDto getById(Long id){
        return commentMapper.toDto(commentDao.getById(id).orElseThrow(() -> new BusinessException(404, "comment.not.found", "Comment not found")));
    }
    @Transactional(readOnly = true)
    public List<CommentDto> getByArticleId(Long articleId) {
        List<Comment> comments = commentDao.findByArticleId(articleId);
        return commentMapper.toDto(comments); // Assuming you have implemented this method in your mapper
    }
    @Transactional
    public void save(CommentDto comment) {
        commentDao.save(commentMapper.toEntity(comment, articleRepository, userRepository));
    }
}