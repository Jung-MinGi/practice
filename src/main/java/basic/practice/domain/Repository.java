package basic.practice.domain;

import java.util.List;

public interface Repository {
    Member save(Member member);
Member findById(Long id);
List<Member> findAll();
}
