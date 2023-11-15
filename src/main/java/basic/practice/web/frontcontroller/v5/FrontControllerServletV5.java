package basic.practice.web.frontcontroller.v5;

import basic.practice.web.frontcontroller.ModelView;
import basic.practice.web.frontcontroller.MyView;
import basic.practice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import basic.practice.web.frontcontroller.v3.controller.MemberListControllerV3;
import basic.practice.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import basic.practice.web.frontcontroller.v4.ControllerV4;
import basic.practice.web.frontcontroller.v4.controller.MemberFormControllerV4;
import basic.practice.web.frontcontroller.v4.controller.MemberListControllerV4;
import basic.practice.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import basic.practice.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import basic.practice.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private Map<String, Object> controllerMap = new HashMap<>();
    private List<MyHandlerAdapter> adapterList = new ArrayList<>();

    public FrontControllerServletV5() {
        setController();
        setAdapter();
    }

    private void setAdapter() {
        adapterList.add(new ControllerV3HandlerAdapter());
        adapterList.add(new ControllerV4HandlerAdapter());
    }

    private void setController() {
        controllerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        controllerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object controller = getController(request);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adapter = getAdapter(controller);
        ModelView mv = adapter.handle(controller, request, response);
        MyView myView = new MyView(viewResolver(mv.getViewName()));
        myView.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getAdapter(Object controller) {
        for (MyHandlerAdapter myHandlerAdapter : adapterList) {
            if (myHandlerAdapter.supports(controller)) {
                return myHandlerAdapter;
            }
        }
        throw new IllegalArgumentException("controller에 적합한 핸들러어댑터가 존재하지 않는다"+controller);
    }
    static String viewResolver(String logicalViewPath) {
        return "/WEB-INF/views/" + logicalViewPath + ".jsp";
    }

    private Object getController(HttpServletRequest request) {
        return controllerMap.get(request.getRequestURI());
    }
}
