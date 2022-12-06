package com.zerobase.lodgingreservationapiserver.configuration;

import com.zerobase.lodgingreservationapiserver.member.exception.DuplicateMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Exception handleAllException() {
        return new Exception("정의되지 않은 예외 발생");
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public String handleDuplicateMemberException() {
        //return new DuplicateMemberException("중복된 회원이 존재합니다.");

        return "중복된 회원이 존재합니다.";
    }
}
