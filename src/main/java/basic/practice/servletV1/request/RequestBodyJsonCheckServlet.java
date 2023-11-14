package basic.practice.servletV1.request;

import basic.practice.JsonPracticeObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonCheckServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonCheckServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        JsonPracticeObject object = objectMapper.readValue(s, JsonPracticeObject.class);
        System.out.println(object.toString());
        System.out.println(object.getUsername());
        System.out.println(object.getClass().equals(JsonPracticeObject.class));
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(object.toString());

    }
}
