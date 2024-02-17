package io.novelis.onlineblog.service.mapper;

import io.novelis.onlineblog.dao.repositories.ArticleRepository;
import io.novelis.onlineblog.dao.repositories.UserRepository;
import io.novelis.onlineblog.domain.Article;
import io.novelis.onlineblog.domain.Comment;
import io.novelis.onlineblog.domain.User;
import io.novelis.onlineblog.service.dto.ArticleDto;
import io.novelis.onlineblog.service.dto.CommentDto;
import io.novelis.onlineblog.service.exception.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "article.id", target = "articleId")
    CommentDto toDto(Comment comment);
    List<CommentDto> toDto(List<Comment> comments);

    Comment toEntity(CommentDto commentDto);

    default Comment toEntity(CommentDto commentDto, ArticleRepository articleRepository, UserRepository userRepository) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        //comment.setCreatedAt(commentDto.getCreatedAt());

        // Fetch the User entity from the database using userId
        Long articleId = commentDto.getArticleId();
        Long userId = commentDto.getUserId();
        if (articleId != null) {
            Article article = articleRepository.findById(articleId)
                    .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + articleId));
            comment.setArticle(article);
        }
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
            comment.setUser(user);
        }

        return comment;
    }



}
