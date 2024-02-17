package io.novelis.onlineblog.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private int status;
    private String title;
    private String message;

}
