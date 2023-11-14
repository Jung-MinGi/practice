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

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository repository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        Member member = new Member(username, Integer.parseInt(age));
        repository.save(member);
        //model에 데이터 넣기
        request.setAttribute("member",member);
        String viewPath = "/WEB-INF/views/save-result.jsp";
        return new MyView(viewPath);
    }
}
