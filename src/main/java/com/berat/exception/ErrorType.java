package com.berat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    UNEXPECTED(1000,"Unexpected error!", INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER(1001,"Invalid parameter entered",BAD_REQUEST),
    NOT_DECODED(1002,"Token could not be decoded",BAD_REQUEST),
    INVALID_TOKEN(1003,"Token not valid",FORBIDDEN),
    HTTP_MESSAGE_NOT_READABLE(1004,"Http message not readable!",BAD_REQUEST),
    INVALID_FORMAT(1005,"Format not valid!",BAD_REQUEST),
    MISSING_PATH_VARIABLE(1006,"Path variable missing!",BAD_REQUEST),
    METHOD_ARGUMENT_TYPE_MISMATCH(1007,"Method argument type mismatch!",BAD_REQUEST),
    TOKEN_NOT_CREATED(1007,"Token not created!",BAD_REQUEST),

    PASSWORD_NOT_MATCH(1100,"Password and rePassword not match",BAD_REQUEST),
    USERNAME_ALREADY_EXIST(1101,"Username already exist!",BAD_REQUEST),
    EMAIL_ALREADY_EXIST(1102,"Email already exist!",BAD_REQUEST),
    INCORRECT_USERNAME_OR_PASSWORD(1103,"Username or password incorrect!",BAD_REQUEST),
    USER_PROFILE_NOT_FOUND(1104,"User profile not found!",NOT_FOUND),
    INVALID_PASSWORD(1105,"Invalid password entered!",NOT_FOUND),

    FOLLOW_ALREADY_EXIST(1200,"Follow already exist!",BAD_REQUEST),
    FOLLOW_NOT_FOUND(1201,"Follow not found!",NOT_FOUND),

    LIKE_ALREADY_EXIST(1300,"Like already exist!",BAD_REQUEST),
    LIKE_NOT_FOUND(1301,"Like not found!",NOT_FOUND),

    SHARE_ALREADY_EXIST(1400,"Share already exist!",BAD_REQUEST),
    SHARE_NOT_FOUND(1401,"Share not found!",NOT_FOUND),

    POST_NOT_FOUND(1500,"Post not found!",NOT_FOUND),
    COMMENT_NOT_FOUND(1600,"Comment not found!",NOT_FOUND),


    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
