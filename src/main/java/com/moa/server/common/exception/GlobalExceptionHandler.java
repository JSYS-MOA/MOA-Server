package com.moa.server.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//컨트롤러에서 예외가 발생하면 여기서 처리
@RestControllerAdvice //@ControllerAdvice(모든 controller 감시) + @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus()) //성공/실패 구분용
                .body(ErrorResponse.of(errorCode)); //프론트 쪽에서 보는용
    }

    @ExceptionHandler(Exception.class)  //예) db연결 끊김
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR));

    }
}
