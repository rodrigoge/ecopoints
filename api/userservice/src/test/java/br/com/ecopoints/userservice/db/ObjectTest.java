package br.com.ecopoints.userservice.db;

import br.com.ecopoints.userservice.mock.MockBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ObjectTest {

    @Test
    void createUser() {
        var user = MockBuilder.buildUser();
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Admin");
        assertThat(user.getEmail()).isEqualTo("admin@admin.com");
        assertThat(user.getPassword()).isEqualTo("12345678");
    }

    @Test
    void createUserTO() {
        var userTO = MockBuilder.buildUserTO();
        assertThat(userTO).isNotNull();
        assertThat(userTO.getId()).isEqualTo(1L);
        assertThat(userTO.getName()).isEqualTo("Admin");
        assertThat(userTO.getEmail()).isEqualTo("admin@admin.com");
    }
}
