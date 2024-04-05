<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Read</title>
</head>
<body>
<div>
    <input type="text" name="tno" value="${dto.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${dto.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dto.dueDate}">
</div>
<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked": ""} readonly >
</div>
<div>
    <a href="/jdbcex/todo/modify?tno=${dto.tno}">수정</a>
    &nbsp;&nbsp;
    <a href="/jdbcex/todo/modify?tno=${dto.tno}">삭제</a>
    <a href="/jdbcex/todo/list">할일 목록으로 가기</a>
</div>
</body>
</html>