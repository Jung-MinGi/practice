package basic.practice.web.frontcontroller.v4.controller;

import basic.practice.domain.Member;
import basic.practice.domain.MemberRepository;
import basic.practice.web.frontcontroller.ModelView;
import basic.practice.web.frontcontroller.v3.ControllerV3;
import basic.practice.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = repository.findAll();
        model.put("members", members);
        return "members";
    }
}
