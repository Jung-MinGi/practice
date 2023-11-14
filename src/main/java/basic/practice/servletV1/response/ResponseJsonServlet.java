package basic.practice.servletV1.response;

import basic.practice.JsonPracticeObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        JsonPracticeObject o = new JsonPracticeObject();
        o.setUsername("JUNG");
        o.setAge(26);
        o.setHeight(178);
        o.setWeight(70);
        String s = objectMapper.writeValueAsString(o);
        response.getWriter().write(s);
    }
}
