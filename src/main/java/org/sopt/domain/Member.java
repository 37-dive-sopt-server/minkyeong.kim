package org.sopt.domain;

import java.time.LocalDate;
import org.sopt.enums.Gender;

public class Member {

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

    public static Member create(Long id, String name, LocalDate birthDate, String email, Gender gender) {
        return new Member(id, name, birthDate, email, gender);
    }

    public Long getId() { return id;}
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getEmail() { return email; }
    public Gender getGender() { return gender; }
}