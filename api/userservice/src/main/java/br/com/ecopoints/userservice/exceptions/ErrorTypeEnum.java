package br.com.ecopoints.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTypeEnum {

    DATABASE("DATABASE"),
    INVALID_REQUEST("INVALID_REQUEST"),
    INTERNAL_SERVER("INTERNAL_SERVER"),
    NOT_FOUND("NOT_FOUND"),
    BAD_GATEWAY("BAD_GATEWAY"),
    MAPPING("MAPPING");

    private final String description;
}
