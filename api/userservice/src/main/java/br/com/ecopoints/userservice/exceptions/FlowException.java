package br.com.ecopoints.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

@Getter
@Log4j2
@AllArgsConstructor
public class FlowException extends RuntimeException {

    private HttpStatus httpStatus;
    private ErrorTypeEnum error;
    private String message;
}
