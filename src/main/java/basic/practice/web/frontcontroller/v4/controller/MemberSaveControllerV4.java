package basic.practice.web.frontcontroller.v4.controller;

import basic.practice.domain.Member;
import basic.practice.domain.MemberRepository;
import basic.practice.web.frontcontroller.ModelView;
import basic.practice.web.frontcontroller.v3.ControllerV3;
import basic.practice.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        String age = paramMap.get("age");
        Member member = new Member(username, Integer.parseInt(age));
        repository.save(member);
        model.put("member", member);
        return "save-result";
    }
}
