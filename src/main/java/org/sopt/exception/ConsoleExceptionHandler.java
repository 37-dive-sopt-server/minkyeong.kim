package org.sopt.exception;

import java.time.format.DateTimeParseException;

public class ConsoleExceptionHandler {
    public static String handle(Exception e) {
        if (e instanceof BusinessException) {
            ErrorCode code = ((BusinessException) e).getErrorCode();
            return "[" + code.getCode() + "] " + code.getMessage();
        }

        if (e instanceof NumberFormatException) {
            return "[" + ErrorCode.INVALID_NUMBER_FORMAT.getCode() + "] " + ErrorCode.INVALID_NUMBER_FORMAT.getMessage();
        }

        if (e instanceof DateTimeParseException) {
            return "[" + ErrorCode.INVALID_DATE_FORMAT.getCode() + "] " + ErrorCode.INVALID_DATE_FORMAT.getMessage();
        }

        if (e instanceof IllegalArgumentException) {
            return "[" + ErrorCode.INVALID_MAPPING_PARAMETER.getCode() + "] " + ErrorCode.INVALID_MAPPING_PARAMETER.getMessage();
        }

        return "[" + ErrorCode.INTERNAL_SERVER_ERROR.getCode() + "] " + ErrorCode.INTERNAL_SERVER_ERROR.getMessage();
    }
}
