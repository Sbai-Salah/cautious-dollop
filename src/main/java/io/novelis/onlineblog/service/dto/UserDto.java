package io.novelis.onlineblog.service.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;

    // Add getters and setters
}