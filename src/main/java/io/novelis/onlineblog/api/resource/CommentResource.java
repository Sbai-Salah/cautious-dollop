package io.novelis.onlineblog.api.resource;

import io.novelis.onlineblog.service.CommentService;
import io.novelis.onlineblog.service.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentResource {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getById(id);
        return ResponseEntity.ok(commentDto);
    }
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<CommentDto>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<CommentDto> commentDtos = commentService.getByArticleId(articleId);
        return ResponseEntity.ok(commentDtos);
    }
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Add more endpoints as needed
}