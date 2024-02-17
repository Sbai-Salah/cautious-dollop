package io.novelis.onlineblog.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private Long userId;
    private Long articleId;
    private LocalDateTime createdAt;

    // Add getters and setters
}