package hello.hellospring.service;

import hello.hellospring.Domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach // 하나의 테스트가 끝나면 저장소들을 비워줘야 한다는 어노테이션
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //Given
        Member member = new Member();
        member.setName("spring");
        // When
        Long saveId = memberService.join(member);
        //Then
        Member result = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(result.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("sadad");
//        }
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}