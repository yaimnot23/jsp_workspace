<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }
    input[type="text"], textarea {
        width: 98%;
        padding: 5px;
    }
    textarea {
        height: 200px;
    }
    button {
        padding: 10px 20px;
        margin-top: 10px;
    }
</style>
</head>
<body>
    <h1>게시판 글쓰기</h1>
    
    <form action="/brd/insert" method="post">
        <table>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" placeholder="제목을 입력하세요" required></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" name="writer" placeholder="작성자를 입력하세요" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content" placeholder="내용을 입력하세요" required></textarea></td>
            </tr>
        </table>
        
        <div style="text-align: center;">
            <button type="submit">등록</button>
            <button type="button" onclick="location.href='list'">취소</button>
        </div>
    </form>

</body>
</html>