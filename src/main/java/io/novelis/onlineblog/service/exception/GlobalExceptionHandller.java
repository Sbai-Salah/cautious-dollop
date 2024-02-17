package io.novelis.onlineblog.service.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandller {

    @ExceptionHandler(BusinessException.class)
    // ResponseEntity in the body : title, message, status
    public ResponseEntity<ProblemDetail> handleBusiness(BusinessException e){
        ProblemDetail problemDetail= ProblemDetail.forStatus(e.getStatus());
        problemDetail.setTitle(e.getTitle());
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(problemDetail);
    }
}
