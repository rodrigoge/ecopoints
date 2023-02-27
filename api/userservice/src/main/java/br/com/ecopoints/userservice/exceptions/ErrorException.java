package br.com.ecopoints.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorException {

    private Integer status;
    private ErrorTypeEnum error;
    private String date;
    private String message;
}
