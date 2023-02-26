package br.com.ecopoints.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserTO {

    private Long id;
    private String name;
    private String email;
}
