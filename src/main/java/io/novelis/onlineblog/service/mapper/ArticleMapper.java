package io.novelis.onlineblog.service.mapper;

import io.novelis.onlineblog.dao.repositories.UserRepository;
import io.novelis.onlineblog.domain.Article;
import io.novelis.onlineblog.domain.User;
import io.novelis.onlineblog.service.dto.ArticleDto;
import io.novelis.onlineblog.service.exception.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {

    @Mapping(source = "user.id", target = "userId")
    ArticleDto toDto(Article article);
    List<ArticleDto> toDto(List<Article> articles);

    Article toEntity(ArticleDto articleDto);

    default Article toEntity(ArticleDto articleDto, UserRepository userRepository) {
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
//        article.setCreatedAt(articleDto.getCreatedAt());

        // Fetch the User entity from the database using userId
        Long userId = articleDto.getUserId();
        System.out.println("article generated from user ID " + userId);

        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
            System.out.println("Username = " + user.getUsername());
            article.setUser(user);
        }

        return article;
    }
}