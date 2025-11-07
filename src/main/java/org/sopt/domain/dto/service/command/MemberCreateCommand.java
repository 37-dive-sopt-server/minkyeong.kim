package org.sopt.domain.dto.service.command;

import java.time.LocalDate;
import java.time.Period;
import org.sopt.domain.enums.Gender;
import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.ErrorCode;

public record MemberCreateCommand (
    String name,
    LocalDate birthDate,
    String email,
    Gender gender
){
    public void validateAge() {
        if (birthDate == null) {
            throw new CustomException(ErrorCode.INVALID_DATE_FORMAT);
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 20) {
            throw new CustomException(ErrorCode.AGE_MUST_UPPER_THAN_20);
        }
    }
}