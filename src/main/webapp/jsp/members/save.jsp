<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="basic.practice.domain.Member"%>
<%@ page import="basic.practice.domain.MemberRepository"%>
<%
MemberRepository repository =  MemberRepository.getInstance();
String username = request.getParameter("username");
        String age = request.getParameter("age");
        Member member = new Member(username, Integer.parseInt(age));
        repository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<li>id=<%=member.getId()%></li>
<li>username=<%=member.getUsername()%></li>
<li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html"> main</a>
</body>
</html>
