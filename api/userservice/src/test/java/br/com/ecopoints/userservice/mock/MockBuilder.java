package br.com.ecopoints.userservice.mock;

import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.dto.UserTO;

public class MockBuilder {

    public static User buildUser() {
        return User
                .builder()
                .id(1L)
                .name("Admin")
                .email("admin@admin.com")
                .password("12345678")
                .build();
    }

    public static UserTO buildUserTO() {
        return UserTO
                .builder()
                .id(1L)
                .name("Admin")
                .email("admin@admin.com")
                .build();
    }
}
