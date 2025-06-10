<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>파라미터 입력</title>
</head>
<body>
    <h3>@RequestParam을 이용한 개별 파라미터 수집</h3>
    <form action="/sample/ex02" method="GET">
        name : <input type="text" name="name" placeholder="Enter name"/><br />
        age : <input type="text" name="age" placeholder="Enter age"/><br />
        <button>Submit</button>
    </form>



    <hr>
    <h3>@RequestParam 속성 활용</h3>
    <form action="/sample/ex02-advanced" method="GET">
        name : <input type="text" name="name" placeholder="Enter name"/><br />
        age : <input type="text" name="age" placeholder="Enter age"/><br />
        <button>Submit</button>
    </form>
</body>
</html>
