package br.com.ecopoints.userservice.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorException {

    private Integer status;
    private ErrorTypeEnum error;
    private String date;
    private String message;
}
