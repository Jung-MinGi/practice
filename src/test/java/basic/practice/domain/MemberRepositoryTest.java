package basic.practice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    void save(){
        Member kim = memberRepository.save(new Member("KIM", 20));
        assertThat(kim.getId()).isEqualTo(0);
    }

    @Test
    void findAll(){
        memberRepository.save(new Member("KIM", 20));
        memberRepository.save(new Member("JUNG", 25));
        memberRepository.save(new Member("PARK", 30));
        List<Member> all = memberRepository.findAll();

        assertThat(all.size()).isEqualTo(3);
    }

}