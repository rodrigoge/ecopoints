package br.com.ecopoints.userservice.services;

import br.com.ecopoints.userservice.db.UserRepository;
import br.com.ecopoints.userservice.mappers.UserMapper;
import br.com.ecopoints.userservice.mock.MockBuilder;
import br.com.ecopoints.userservice.utils.Utils;
import br.com.ecopoints.userservice.validators.UserValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private Utils utils;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldCreateNewUser() {
        var user = MockBuilder.buildUser();
        var userTO = MockBuilder.buildUserTO();
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userMapper.fromUser(user)).thenReturn(userTO);
        Assertions.assertThat(userService.create(user)).isNotNull();
        Assertions.assertThat(userService.create(user)).isEqualTo(userTO);
    }
}
