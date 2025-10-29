package org.sopt.global.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.sopt.global.common.enums.Status;
import org.sopt.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {
    private final Status status;
    private final int statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String errorCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String errorName;

    private ApiResponse(Status status, int statusCode, T data, String message, String errorCode, String errorName) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.errorCode = errorCode;
        this.errorName = errorName;
    }

    // 성공 (데이터 포함)
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, HttpStatus.OK.value(), data, null, null, null));
    }

    // 성공 (데이터 없음)
    public static ResponseEntity<ApiResponse<Void>> success() {
        return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, HttpStatus.OK.value(), null, null, null, null));
    }

    // 실패 (검증/요청 오류)
    public static ResponseEntity<ApiResponse<Void>> fail(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse<>(Status.FAIL, 400, null, message, null, null));
    }

    // 에러 (비즈니스/서버)
    public static ResponseEntity<ApiResponse<Void>> error(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(new ApiResponse<>(Status.ERROR, errorCode.getHttpStatus().value(), null,
                errorCode.getMessage(), errorCode.getErrorCode(), errorCode.name()));
    }

    public Status getStatus() { return status; }
    public int getStatusCode() { return statusCode; }
    public T getData() { return data; }
    public String getMessage() { return message; }
    public String getErrorCode() { return errorCode; }
    public String getErrorName() { return errorName; }
}
