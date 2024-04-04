<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jdbcex/todo/register" method="post">
    <div>
        <input type="text" name="title" placeholder="해야할 일을 넣으세요.">
    </div>
    <br><br>
    <div>
        <input type="date" name="dueDate">까지
    </div>
    <br><br>
    <div>
        <button type="submit"> 해야할 일 등록 </button>
        <button type="reset"> 다시 입력 </button>
    </div>
</form>
</body>
</html>
