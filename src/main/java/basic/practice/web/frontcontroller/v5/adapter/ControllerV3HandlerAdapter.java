package basic.practice.web.frontcontroller.v5.adapter;

import basic.practice.web.frontcontroller.ModelView;
import basic.practice.web.frontcontroller.v3.ControllerV3;
import basic.practice.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public ModelView handle(Object handler, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        return controller.process(extracted(request));
    }

    private static Map<String, String> extracted(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(headerName -> paramMap.put(headerName, request.getParameter(headerName)));
        return paramMap;
    }
}
