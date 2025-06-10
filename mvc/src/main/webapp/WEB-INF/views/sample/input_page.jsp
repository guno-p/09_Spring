<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>파라미터 입력</title>
</head>
<body>
    <h3>DTO 이용한 파라미터 수집</h3>

<%--
    input 의 name 속성은 파라미터의 Key 값이 된다.
--%>
    <form action="/sample/ex01" method="POST">
        name : <input type="text" name="name" placeholder="Enter name"/><br />
        age : <input type="text" name="age" placeholder="Enter age"/><br />
        <button>Submit</button>
    </form>
</body>
</html>
