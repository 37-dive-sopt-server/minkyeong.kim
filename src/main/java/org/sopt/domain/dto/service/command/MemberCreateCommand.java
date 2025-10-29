package org.sopt.domain.dto.service.command;

import java.time.LocalDate;
import org.sopt.domain.enums.Gender;

public record MemberCreateCommand (
    String name,
    LocalDate birthDate,
    String email,
    Gender gender
){}
