package hello.hellospring.repository;

import hello.hellospring.Domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //null 이 반환될 가능성이 있으면 Optional.ofNullable   을 사용
        // 얘는 반환값이 널이어도 감싸서 반환해주는 역할을 한다
    }

    @Override
    public Optional<Member> findByName(String name) {
        //자바 람다를 사용했다
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //name이랑 같은 값이 있으면 findany가 반환해주고 아니면 optional 에 널 값을 포함해서 반환시킴
        // 아주 똒똑쓰
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
