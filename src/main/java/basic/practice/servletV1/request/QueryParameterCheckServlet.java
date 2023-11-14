package basic.practice.servletV1.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "queryParameterCheckServlet", urlPatterns = "/query-parameter")
public class QueryParameterCheckServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> System.out.println(paramName+" : "+request.getParameter(paramName)));
    }
}
