<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 화면 보기</h1>

<table>
    <tr>
        <th>num</th>
        <th>title</th>
        <th>writer</th>
        <th>regdate</th>
    </tr>
<c:forEach items="${list}" var="b">
    <tr>
        <td>${b.bno}</td>
        <td>${b.title}</a></td>
        <td>${b.writer}</td>
        <td>${b.regdate}</td>
     </tr>
</c:forEach>
    
    <%-- Sample data row --%>
    <tr>
        <td>1</td>
        <td><a href="/brd/detail?bno=1">첫 번째 게시글 제목</a></td>
        <td>홍길동</td>
        <td>2024-06-01</td>
    </tr>
    <%-- Additional rows would be populated here dynamically --%>

</body>
</html>