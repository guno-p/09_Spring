<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>DTO로 파라미터 수집</title>
</head>
<body>
<%-- 사용할때는 커맨드객체를 camelCase 로 바꾼 형태로 사용 --%>
name : ${sampleDTO.name} <br>
age : ${sampleDTO.age}
</body>
</html>