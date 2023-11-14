package basic.practice.web.frontcontroller.v3.controller;

import basic.practice.domain.Member;
import basic.practice.domain.MemberRepository;
import basic.practice.web.frontcontroller.ModelView;
import basic.practice.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        System.out.println("username = " + username);
        String age = paramMap.get("age");
        System.out.println("age = " + age);
        Member member = new Member(username, Integer.parseInt(age));
        repository.save(member);
        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);
        return modelView;
    }
}
