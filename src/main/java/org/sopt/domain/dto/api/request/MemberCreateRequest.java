package org.sopt.domain.dto.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import org.sopt.domain.dto.service.command.MemberCreateCommand;
import org.sopt.domain.enums.Gender;

public record MemberCreateRequest(
    @NotBlank(message = "이름은 필수입니다.")
    String name,

    @NotNull(message = "생년월일은 필수입니다.")
    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    LocalDate birthDate,

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,

    @NotNull(message = "성별은 필수입니다.")
    Gender gender
) {
    public MemberCreateCommand toCommand() {
        return new MemberCreateCommand(name, birthDate, email, gender);
    }
}