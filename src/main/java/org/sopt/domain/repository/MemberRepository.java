package org.sopt.domain.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.domain.entity.Member;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    boolean existsByEmail(String email);

    boolean deleteById(Long id);

    List<Member> findAll();
}
