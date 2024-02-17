package io.novelis.onlineblog.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    public String password;
    public String userName;
}

