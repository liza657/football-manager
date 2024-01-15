package com.example.football_manager.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
class ErrorMessage {

    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private ErrorMessage() {
        timestamp = LocalDateTime.now();
    }

    ErrorMessage(HttpStatus status) {
        this();
        this.status = status;
    }
}
