package br.com.ecopoints.userservice.validators;

import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.db.UserRepository;
import br.com.ecopoints.userservice.exceptions.ErrorTypeEnum;
import br.com.ecopoints.userservice.exceptions.FlowException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validatingNullableFieldsUser(User request) {
        log.info("Validating request for user.");
        if (request == null ||
                request.getName() == null ||
                request.getEmail() == null ||
                request.getPassword() == null)
            throw new FlowException(
                    HttpStatus.BAD_REQUEST,
                    ErrorTypeEnum.INVALID_REQUEST,
                    "Invalid request for user. " + request
            );
        log.info("Finishing nullable validation.");
    }

    public void validatingEmailAlreadyExists(String email) {
        log.info("Validating user email already exist.");
        if (userRepository.findByEmail(email).isPresent())
            throw new FlowException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorTypeEnum.INTERNAL_SERVER,
                    "E-mail already exists. " + email
            );
        log.info("Finishing email validation.");
    }
}
