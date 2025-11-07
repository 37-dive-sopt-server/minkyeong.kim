package org.sopt.domain.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.sopt.domain.dto.service.command.MemberCreateCommand;
import org.sopt.domain.enums.Gender;
import org.sopt.global.common.entity.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SQLRestriction("deleted_at is null")
@SQLDelete(sql = "UPDATE member SET deleted_at = now() WHERE id = ?")
@Comment("회원 정보")
public class Member extends BaseEntity {

    @Id
    @Tsid
    @Column(length = 13)
    @Comment("회원 PK")
    private String id;

    @Column(nullable = false, length = 50)
    @Comment("이름")
    private String name;

    @Column(nullable = false, length = 100)
    @Comment("이메일")
    private String email;

    @Column(nullable = false)
    @Comment("생년월일")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    @Comment("성별")
    private Gender gender;

    @Builder
    private Member(String name, LocalDate birthDate, String email, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
    }

    public static Member create(MemberCreateCommand command) {
        return Member.builder()
            .name(command.name())
            .birthDate(command.birthDate())
            .email(command.email())
            .gender(command.gender())
            .build();
    }
}