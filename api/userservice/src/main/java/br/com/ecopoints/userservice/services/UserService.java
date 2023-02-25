package br.com.ecopoints.userservice.services;

import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.db.UserRepository;
import br.com.ecopoints.userservice.dto.UserTO;
import br.com.ecopoints.userservice.mappers.UserMapper;
import br.com.ecopoints.userservice.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Utils utils;

    public UserTO create(User request) {
        log.info("Starting create a new user flow.");
        utils.validUserRequest(request);
        log.info("Saving a new user in database." + request);
        var user = userRepository.save(request);
        log.info("Mapping user object." + user);
        var userTO = userMapper.fromUser(user);
        log.info("Finishing creation of a new user." + userTO);
        return userTO;
    }
}
