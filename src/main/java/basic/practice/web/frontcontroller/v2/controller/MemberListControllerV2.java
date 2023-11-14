package basic.practice.web.frontcontroller.v2.controller;

import basic.practice.domain.Member;
import basic.practice.domain.MemberRepository;
import basic.practice.web.frontcontroller.MyView;
import basic.practice.web.frontcontroller.v1.ControllerV1;
import basic.practice.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = repository.findAll();
        request.setAttribute("members", members);
        String viewPath = "/WEB-INF/views/members.jsp";
        return new MyView(viewPath);
    }
}
