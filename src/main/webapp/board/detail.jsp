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
        <a href="/brd/remove?bno=${b.bno }"><button>삭제</button></a>
        <a href="/brd/list"><button>리스트</button></a>
        
        <div>
        <h3>댓글 목록</h3>
        <input type="text" id="cmtWriter" placeholder="writer..."><br>
        <input type="text" id="cmtText" placeholder="Add Comment...">
        <button type="button" id="cmtAddBtn">댓글 추가</button>
        

        
        
        </div>
        
        <!-- 댓글 출력 라인 -->
        <div id="commentLine">
        <div>
        <div>cno, bno, writer, regdate
        <div>
             <input type="text" value="댓글 내용">
             <button type="button">수정</button>
             <button type="button">삭제</button>
        </div>
        </div>
        </div>
        </div>
        <script type="text/javascript">
const bnoVal = `<c:out value="${b.bno}" />`;
console.log("bnoVal : " + bnoVal);
</script>

<script type="text/javascript" src="/resources/boardDetail.js"></script>
        
 
</body>
</html>