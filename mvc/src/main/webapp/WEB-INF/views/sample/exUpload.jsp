<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>파일 업로드 테스트</title>
</head>
<body>

<%--
    * method="post" : 파일 업로드는 POST 방식만 가능

    * enctype : 서버로 제출되는 데이터의 인코딩 지정하는 속성
       - application/x-www-form-urlencoded (기본값): 일반 텍스트 폼
       - multipart/form-data: 텍스트와 바이너리 데이터를 구분해서 전송 (파일 업로드용)
       - text/plain: 단순 텍스트 (거의 사용 안함)

    * accept-charset="UTF-8" : 문자 인코딩 방식 지정
--%>
<form action="/sample/exUploadPost" method="post" enctype="multipart/form-data" accept-charset="UTF-8" >
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>

  <div>
    <input type="submit" />
  </div>
</form>
</body>
</html>