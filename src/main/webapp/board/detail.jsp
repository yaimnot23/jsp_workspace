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
	<h1>게시판 상세보기</h1>
	<table>
        <tr>
            <th>번호</th>
            <td>${b.bno}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td>${b.title}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${b.writer}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td>${b.content}</td>
        </tr>
        <tr>
            <th>등록일</th>
            <td>${b.regdate}</td>
        </tr>
        </table>
        
        <!-- 수정, 삭제, 리스트 버튼 -->
        <a href="/brd/modify?bno=${b.bno }"><button>수정</button></a>
        <a href=""><button>삭제</button></a>
        <a href="/brd/list"><button>리스트</button></a>

</body>
</html>