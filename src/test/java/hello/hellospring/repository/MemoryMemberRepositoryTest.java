package hello.hellospring.repository;

import hello.hellospring.Domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach // 하나의 테스트가 끝나면 저장소들을 비워줘야 한다는 어노테이션
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get();

        Assertions.assertThat(member1).isEqualTo(result);

    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("spring");
        memberRepository.save(member);


        Member member2 = new Member();
        member2.setName("spring1");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);



    }
}