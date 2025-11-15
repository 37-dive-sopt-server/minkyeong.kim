package org.sopt.domain.dto.service.command;

import java.time.LocalDate;
import org.sopt.domain.enums.ArticleTag;

public record ArticleCreateCommand(
    String memberId,
    ArticleTag tag,
    LocalDate articleDate,
    String title,
    String content
) {}
