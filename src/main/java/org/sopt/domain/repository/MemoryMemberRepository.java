package org.sopt.domain.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.sopt.domain.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsByEmail(String email) {
        if (email == null) return false;
        return store.values().stream()
            .anyMatch(m -> email.equalsIgnoreCase(m.getEmail()));
    }

    @Override
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
