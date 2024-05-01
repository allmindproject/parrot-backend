package beaverbackend.controllers.common;

import beaverbackend.enums.BadRequestDictEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BadRequestRes> handeJsonParseException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new BadRequestRes(BadRequestDictEnum.BAD_VALUE, e.getMessage()));
    }

}
