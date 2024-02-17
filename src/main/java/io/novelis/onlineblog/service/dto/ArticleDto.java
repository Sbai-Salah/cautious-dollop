package io.novelis.onlineblog.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;

    // Add getters and setters
}