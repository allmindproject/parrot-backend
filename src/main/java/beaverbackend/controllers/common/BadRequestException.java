package beaverbackend.controllers.common;

import beaverbackend.enums.BadRequestDictEnum;

public class BadRequestException extends Exception {

    private final BadRequestDictEnum badRequestCode;
    private final String value;

    public BadRequestException(BadRequestDictEnum badRequestCode, String value) {
        this.badRequestCode = badRequestCode;
        this.value = value;
    }

    public BadRequestRes getResponse() {
        return new BadRequestRes(this.badRequestCode, this.value);
    }

}
