package org.sopt.domain.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

}
