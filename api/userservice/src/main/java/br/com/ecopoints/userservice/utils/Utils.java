package br.com.ecopoints.userservice.utils;

import br.com.ecopoints.userservice.db.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Utils {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void encryptPassword(User user) {
        log.info("Encrypting user password. " + user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
