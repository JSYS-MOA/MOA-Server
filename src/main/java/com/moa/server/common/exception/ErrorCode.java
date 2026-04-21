package com.moa.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//enum이란 상수(미리 정해진) 목록을 정의한 것 = 에러 종류를 미리 정해둠
public enum ErrorCode {

    /*
        C = Common(공통)
        A = Auth(인증)
        U = User(유저)

        C001 = 서버오류   => 500
        C002 = 잘못된 요청   => 400

        A001 = 로그인 실패  => 401
        A002 = 로그인 안됨 (세션 없음)  => 401
        A003 = 권한없음   => 403

        U001 = 유저없음  => 404

        ----------------------------------------

        2xx - 성공

        4xx - 클라이언트 오류(프론트쪽 오류)
            400 - 잘못된 요청(데이터 형식 틀림)
            401 - 로그인 안됨(인증 필요)
            403 - 권한없음
            404 - 없는 url 요청

         5xx - 서버오류
            500 - 서버 내부 오류
            502 - 서버 연결 오류

    * */

    //공통
    INTERNAL_SERVER_ERROR(500, "C001", "서버 오류가 발생했습니다"),
    INVALID_REQUEST(400, "C002", "잘못된 요청입니다"),

    //인증
    LOGIN_FAILED(401, "A001", "사원코드 또는 비밀번호를 확인해주세요"),
    FORBIDDEN(403, "A003", "접근 권한이 없습니다"),
    UNAUTHORIZED(401, "A002", "로그인이 필요합니다"),

    FILE_NOT_FOUND(404,"F001","파일을 찾을 수 없습니다"),

    //유저
    USER_NOT_FOUND(404, "U001", "사용자를 찾을 수 없습니다");

    private final int status;
    private final String code;
    private final String message;

}