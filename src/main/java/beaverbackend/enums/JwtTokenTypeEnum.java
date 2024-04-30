package beaverbackend.enums;

import lombok.Getter;

@Getter
public enum JwtTokenTypeEnum {
    BEARER("Bearer");

    private final String header;

    private JwtTokenTypeEnum(String header) {
        this.header = header;
    }
}
