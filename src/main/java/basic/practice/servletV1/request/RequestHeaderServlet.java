package basic.practice.servletV1.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * request header 조회하기
 */
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String s = headerNames.nextElement();
//            sb.append(s).append(" :: ");
//            sb.append(request.getHeader(s)).append('\n');
//        }
//        response.getWriter().write(sb.toString());
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName-> System.out.println(headerName+" : "+request.getHeader(headerName)));
    }
}
