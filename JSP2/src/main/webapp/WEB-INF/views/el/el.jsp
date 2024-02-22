<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
	<h1>EL을 이용해서 request에 세팅된 속성 얻어와 출력하기</h1>
	
	<ul>
		<li> [작성법] : \${K} </li>
		
		<li> 기본 자료형  : ${score}</li>
		
		<li> String(객체) : ${address}</li>
		
		<li>
			List 객체 : ${strList}
			
			<!-- EL 구문에서는 배열/리스트 관계 없이 요소 하나 얻어올 때 
					[index] 사용 -->
			<ul>
				<li>${strList[0]}</li>
				<li>${strList[1]}</li>
				<li>${strList[2]}</li>
				<li>${strList[3]}</li>
			</ul>
		</li>
		
		
		<li>
			DTO : ${book1}
			<ul>
				<%--  ${K.필드명} : 객체의 필드 값을 얻어오기 위한 getter 호출  --%>
				
				<!-- **** EL을 이용해서 필드 값을 얻어오려면 getter가 필수!! **** -->
			
				<li>${book1.title}</li>  <!-- book1.getTitle() -->
				<li>${book1.writer}</li>				
				<li>${book1.price}</li>				
			</ul>
		</li>
		
	</ul>
	
  <hr><hr>
	
	<h1> empty 연산자 </h1>
  <pre>
    - \${empty 객체 | 배열 | 리스트}

    - null 또는 빈칸 또는 비어있으면 true
      아니면 false

    - EL은 null을 빈칸으로 표현
      -> null == ""(빈칸)  ==  비어있음(출력할게 없어서 빈칸)
  </pre>

  <ul>
    <li> test1 = null; ->   ${empty test1}</li>
    <li> test2 = ""; ->     ${empty test2}</li>
    <li> test3 = null; ->   ${empty test3}</li>
    <li> test4 = new ArrayList<String>(); ->  ${empty test4}</li> <!-- 1~4는 true  -->
    <li> test5 = new ArrayList<String>(); + add()->  ${empty test5}</li> <!-- false -->
  </ul>
	
	 <hr>
	 
	 <h1>EL을 이용해 파라미터 출력하기</h1>
    <pre>
     - \ ${param.key } 형식으로 작성
      
      * 테스트 진행 시 쿼리스트링 이용 * 
      
      - 퀴리스트링 : 주소에 담긴 값을 나타내는 문자열
      
      -> 요청주소?K=V&K=V ....
      
      - forma 태그 GET 방식 제출
        또는
        a태그, JS, 주소창에 직접 작성하는 것도 가능!
    
    </pre>
   
   <h3>\${param.input } : ${param.input }</h3> <!-- 앞이 K 화면에 보이는 것 뒤가 V 값-->
  
   <h3>\${param.message} : ${param.message }</h3>
   <!-- 주소에는 띄어쓰기 하면 안됨  -->
  
  
  
</body>
</html>