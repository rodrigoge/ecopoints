package br.com.ecopoints.userservice.api;

import br.com.ecopoints.userservice.mock.MockBuilder;
import br.com.ecopoints.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserApiControllerTest {

    @InjectMocks
    private UserApiController userApiController;

    @Mock
    private UserService userService;

    @Test
    void create() {
        var user = MockBuilder.buildUser();
        var response = userApiController.create(user);
        assertThat(response).isNotNull();
        assertThat(userApiController.create(user)).isEqualTo(response);
    }
}
