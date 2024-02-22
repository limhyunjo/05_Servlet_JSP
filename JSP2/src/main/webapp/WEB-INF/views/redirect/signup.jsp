<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirect 확인 하기</title>
</head>
<body>
 
 <!--session에 message가 존재하면 출력됨  -->
 <h1>${message}</h1>
  
  <% // session에 존재하는 속성 중 message를 제거
     session.removeAttribute("message"); // 한 번만 출력하고 끝남  
  
    //redirect 시 데이터를 유지하기 위해 session을 이용했는데
    //1회성으로 사용 불가능(계속 유지되고 있음....)
    // -> 1회 출력 후 session에서 데이터를 제거
    %>
 
 
 
 <hr>
 
 <h1>회원 가입</h1>

  <form action="/signup" method="POST">
  
    <div>
      ID : <input type="text" name="id">
    </div>
    <div>
      PW : <input type="password" name="pw">
    </div>
    <div>
      PW CHECK : <input type="password" name="pwCheck">
      
      <button>회원 가입</button>
    </div>
  </form>

</body>
</html>