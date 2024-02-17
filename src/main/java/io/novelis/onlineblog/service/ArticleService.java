package io.novelis.onlineblog.service;

import io.novelis.onlineblog.dao.RepositoriesDao.ArticleDao;
import io.novelis.onlineblog.dao.repositories.UserRepository;
import io.novelis.onlineblog.domain.Article;
import io.novelis.onlineblog.domain.User;
import io.novelis.onlineblog.service.dto.ArticleDto;
import io.novelis.onlineblog.service.dto.UserDto;
import io.novelis.onlineblog.service.exception.BusinessException;
import io.novelis.onlineblog.service.mapper.ArticleMapper;
import io.novelis.onlineblog.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository; // Inject UserRepository
    @Transactional(readOnly = true)
    public ArticleDto getById(Long id){
        return articleMapper.toDto(articleDao.getById(id).orElseThrow(() -> new BusinessException(404, "article.not.found", "Article not found")));
    }

    @Transactional(readOnly = true)
    public List<ArticleDto> findAll() {
        return articleMapper.toDto(articleDao.findAll());
    }

    @Transactional
    public void save(ArticleDto article) {
        articleDao.save(articleMapper.toEntity(article, userRepository));
    }



}
