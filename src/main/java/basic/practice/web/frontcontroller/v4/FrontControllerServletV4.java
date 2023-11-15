package basic.practice.web.frontcontroller.v4;

import basic.practice.web.frontcontroller.ModelView;
import basic.practice.web.frontcontroller.MyView;
import basic.practice.web.frontcontroller.v3.ControllerV3;
import basic.practice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import basic.practice.web.frontcontroller.v3.controller.MemberListControllerV3;
import basic.practice.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import basic.practice.web.frontcontroller.v4.controller.MemberFormControllerV4;
import basic.practice.web.frontcontroller.v4.controller.MemberListControllerV4;
import basic.practice.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerV4 controller = controllerMap.get(request.getRequestURI());
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = extracted(request);
        Map<String, Object> mv = new HashMap<>();
        String viewName = controller.process(paramMap, mv);

        String viewPath = viewResolver(viewName);
        MyView myView = new MyView(viewPath);
        myView.render(mv, request, response);
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
