package basic.practice.web.frontcontroller.v3;

import basic.practice.web.frontcontroller.ModelView;
import basic.practice.web.frontcontroller.MyView;
import basic.practice.web.frontcontroller.v2.ControllerV2;
import basic.practice.web.frontcontroller.v2.controller.MemberFormControllerV2;
import basic.practice.web.frontcontroller.v2.controller.MemberListControllerV2;
import basic.practice.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import basic.practice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import basic.practice.web.frontcontroller.v3.controller.MemberListControllerV3;
import basic.practice.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request = " + request.getRequestURI());
        ControllerV3 controller = controllerMap.get(request.getRequestURI());
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = extracted(request);
        ModelView mv = controller.process(paramMap);

        String viewPath = viewResolver(mv.getViewName());
        MyView myView = new MyView(viewPath);
        myView.render(mv.getModel(), request, response);
    }

    static String viewResolver(String logicalViewPath) {
        return "/WEB-INF/views/" + logicalViewPath + ".jsp";
    }

    private static Map<String, String> extracted(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(headerName -> paramMap.put(headerName, request.getParameter(headerName)));
        return paramMap;
    }
}
