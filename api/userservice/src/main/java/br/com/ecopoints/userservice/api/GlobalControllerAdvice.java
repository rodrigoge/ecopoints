package br.com.ecopoints.userservice.api;

import br.com.ecopoints.userservice.exceptions.ErrorException;
import br.com.ecopoints.userservice.exceptions.FlowException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(FlowException.class)
    public ResponseEntity<ErrorException> handleFlowException(FlowException exception) {
        var status = exception.getHttpStatus().value();
        var error = exception.getError();
        var message = exception.getMessage();
        return ResponseEntity
                .status(status)
                .body(ErrorException
                        .builder()
                        .status(status)
                        .error(error)
                        .date(LocalDateTime.now(ZoneId.of("UTC")).toString())
                        .message(message)
                        .build());
    }
}
