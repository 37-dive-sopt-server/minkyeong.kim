package org.sopt.domain.entity;

import java.time.LocalDate;
import org.sopt.domain.dto.service.command.MemberCreateCommand;
import org.sopt.domain.enums.Gender;
import org.sopt.global.common.entity.BaseEntity;
import org.sopt.global.utils.IdGenerator;

public class Member extends BaseEntity {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private Gender gender;

    private Member(Long id, String name, LocalDate birthDate, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
    }

    public static Member create(MemberCreateCommand command) {
        return new Member(
            IdGenerator.next(),
            command.name(),
            command.birthDate(),
            command.email(),
            command.gender()
        );
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getEmail() { return email; }
    public Gender getGender() { return gender; }
}