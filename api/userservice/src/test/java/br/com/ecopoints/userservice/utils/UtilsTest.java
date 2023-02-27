package br.com.ecopoints.userservice.utils;

import br.com.ecopoints.userservice.mock.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UtilsTest {

    @InjectMocks
    private Utils utils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldEncryptPassword() {
        var user = MockBuilder.buildUser();
        utils.encryptPassword(user);
        Assertions.assertThat(user).isNotNull();
    }
}
