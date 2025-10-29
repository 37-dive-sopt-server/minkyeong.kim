package org.sopt.global.exception;

import jakarta.validation.ConstraintViolationException;
import org.sopt.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 비즈니스/커스텀 에러 */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
        return ApiResponse.error(e.getErrorCode());
    }

    /** @Valid - RequestBody 검증 실패 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        FieldError fe = (FieldError) e.getBindingResult().getAllErrors().get(0);
        return ApiResponse.fail(fe.getDefaultMessage());
    }

    /** 타입 미스매치 (Path/Query 등) */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ApiResponse.error(ErrorCode.INVALID_PARAMETER);
    }

    /** 필수 파라미터 누락 */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingParam(MissingServletRequestParameterException e) {
        return ApiResponse.error(ErrorCode.INVALID_PARAMETER);
    }

    /** JSON 파싱 실패 */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotReadable(HttpMessageNotReadableException e) {
        return ApiResponse.error(ErrorCode.INVALID_PARAMETER);
    }

    /** 파라미터 레벨 제약 위반 */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException e) {
        return ApiResponse.error(ErrorCode.INVALID_PARAMETER);
    }

    /** 없는 경로 / 지원하지 않는 메서드 */
    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiResponse<Void>> handleNotFound(Exception e) {
        return ApiResponse.error(ErrorCode.NOT_FOUND);
    }

    /** 기타 예외 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}