package org.sopt.exception;

public enum ErrorCode {

    // 400 BAD REQUEST
    INVALID_NUMBER_FORMAT(400, "B001", "숫자만 입력해주세요."),
    INVALID_DATE_FORMAT(400, "B002", "잘못된 날짜 형식입니다."),
    INVALID_MAPPING_PARAMETER(400, "B003", "매핑할 수 없는 값입니다."),
    EMAIL_BLANK(400, "B004", "이메일을 입력해주세요."),
    AGE_MUST_UPPER_THAN_20(400, "B005", "20세 미만은 가입할 수 없습니다."),

    // 404 NOT FOUND
    MEMBER_NOT_FOUND(404, "N001", "존재하지 않는 회원입니다."),

    // 409 CONFLICT
    MEMBER_BY_EMAIL_ALREADY_EXISTS(409, "C001", "해당 이메일로 가입된 회원이 이미 존재합니다."),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "S001", "서버 내부 오류가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
