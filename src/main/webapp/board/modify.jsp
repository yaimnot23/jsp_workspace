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
	<h1>수정 상세보기</h1>
	<form action="/brd/update" method="post">
	<table>
        <tr>
            <th>번호</th>
            <td>${b.bno}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td>
            	<input type="text" name="title" value="${b.title}">
            </td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${b.writer}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td>
            <textarea rows="10" cols"30 name="content" value=""></textarea>
            </td>
        </tr>
        <tr>
            <th>등록일</th>
            <td>${b.regdate}</td>
        </tr>
        </table>
        <button type="submit">수정</button>
        <button type="reset">취소</button>
        </form>
        

</body>
</html>