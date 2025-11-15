package org.sopt.domain.dto.api.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import org.sopt.domain.dto.service.command.ArticleCreateCommand; // ✅ 정확한 import
import org.sopt.domain.enums.ArticleTag;

public record ArticleCreateRequest(
    @NotNull(message = "태그는 필수입니다.")
    ArticleTag tag,

    @NotNull(message = "작성일은 필수입니다.")
    @PastOrPresent(message = "작성일은 과거 또는 오늘이어야 합니다.")
    LocalDate articleDate,

    @NotBlank(message = "제목은 필수입니다.")
    String title,

    @NotBlank(message = "내용은 필수입니다.")
    String content
) {
    public ArticleCreateCommand toCommand(String userId) {
        return new ArticleCreateCommand(userId, tag, articleDate, title, content);
    }
}
