package org.sopt.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    /* ========== 공통 ========== */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ISE", "서버 내부 오류입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FBD", "권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNA", "인증되지 않았습니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "BR", "잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NF", "존재하지 않는 리소스입니다."),

    /* ========== 도메인 ========== */
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "B002", "잘못된 날짜 형식입니다."),
    EMAIL_BLANK(HttpStatus.BAD_REQUEST, "B004", "이메일을 입력해주세요."),
    AGE_MUST_UPPER_THAN_20(HttpStatus.BAD_REQUEST, "B005", "20세 미만은 가입할 수 없습니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "N001", "존재하지 않는 회원입니다."),
    MEMBER_BY_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "C001", "해당 이메일로 가입된 회원이 이미 존재합니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    public HttpStatus getHttpStatus() { return httpStatus; }
    public String getErrorCode() { return errorCode; }
    public String getMessage() { return message; }
}