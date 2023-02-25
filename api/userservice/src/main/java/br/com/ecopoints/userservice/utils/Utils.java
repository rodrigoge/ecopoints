package br.com.ecopoints.userservice.utils;

import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.exceptions.ErrorTypeEnum;
import br.com.ecopoints.userservice.exceptions.FlowException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Utils {

    public void validUserRequest(User request) {
        log.info("Validating request for user.");
        if (request == null ||
                request.getName() == null ||
                request.getEmail() == null ||
                request.getPassword() == null)
            throw new FlowException(
                    HttpStatus.BAD_REQUEST,
                    ErrorTypeEnum.INVALID_REQUEST,
                    "Invalid request for user." + request
            );
        log.info("Finishing validation.");
    }
}
