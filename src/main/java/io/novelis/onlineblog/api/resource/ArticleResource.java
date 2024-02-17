package io.novelis.onlineblog.api.resource;

import io.novelis.onlineblog.service.ArticleService;
import io.novelis.onlineblog.service.dto.ArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleResource {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id) {
        ArticleDto articleDto = articleService.getById(id);
        return ResponseEntity.ok(articleDto);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        List<ArticleDto> articleDtoList = articleService.findAll();
        return ResponseEntity.ok(articleDtoList);
    }

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody ArticleDto articleDto) {
        articleService.save(articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Add more endpoints as needed
}
