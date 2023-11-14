<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="basic.practice.domain.Member"%>
<%@ page import="basic.practice.domain.MemberRepository"%>
<%@ page import="java.util.List"%>
<%
MemberRepository repository =  MemberRepository.getInstance();
 List<Member> members = repository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <a href=/index.html>메인</a>
        <table>
            <thead>
            <th>id</th>
            <th>username</th>
            <th>age</th>
            </thead>
            <tbody>
            <%
        for (Member member : members) {
            out.write("    <tr>");
            out.write("        <td>" + member.getId() + "</td>");
            out.write("        <td>" + member.getUsername() + "</td>");
            out.write("        <td>" + member.getAge() + "</td>");
            out.write("    </tr>");
        }
        %>
   </tbody>
    </table>
   </body>
</html>
