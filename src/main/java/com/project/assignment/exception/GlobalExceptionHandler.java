package com.project.assignment.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(Exception exception) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(HttpStatus.NOT_FOUND, LocalDateTime.now(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(Exception exception) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }
    @ExceptionHandler({InternalServerException.class, Exception.class})
    public ResponseEntity<?> handleInternalServerErrorException(Exception exception) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(Exception exception) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(HttpStatus.BAD_REQUEST, LocalDateTime.now(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
    }
}
