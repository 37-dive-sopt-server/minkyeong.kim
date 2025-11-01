package org.sopt.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import org.sopt.domain.dto.service.command.MemberCreateCommand;
import org.sopt.domain.enums.Gender;
import org.sopt.global.common.entity.BaseEntity;
import org.sopt.global.utils.IdGenerator;

@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    private Gender gender;

    protected Member() {
    }

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