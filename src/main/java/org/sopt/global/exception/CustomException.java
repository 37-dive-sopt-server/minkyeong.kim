package org.sopt.global.exception;

public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getErrorCode());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() { return errorCode; }
}
