package basic.practice.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository implements Repository{

    private static  Map<Long, Member> repository = new HashMap<>();

    private static long index = 0;

    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {
    }

    public static MemberRepository getInstance() {
        return instance;
    }

    @Override
    public Member save(Member member) {
        member.setId(index++);
        repository.put(index,member);
        return member;
    }

    @Override
    public Member findById(Long id) {
         return repository.get(id);
    }

    @Override
    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        repository.keySet().stream().iterator()
                .forEachRemaining(key->list.add(repository.get(key)));
        return list;
    }
    public void clear(){
        index=0;
        repository.clear();
    }
}