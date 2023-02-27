package br.com.ecopoints.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTypeEnum {

    DATABASE,
    INVALID_REQUEST,
    INTERNAL_SERVER,
    NOT_FOUND,
    BAD_GATEWAY,
    MAPPING;
}
