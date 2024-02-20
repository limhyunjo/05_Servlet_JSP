package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*Servlet 작성 순서
 * 1) HttpServlet 상속 받기 (extends / 클래스 끼리 상속)
 * 
 * 2) @WebServlet( ) 어노테이션 작성하기
 * 
 * 3) doGet( ) 또는 doPost( ) 오버라이딩 -> method에 따라 수행
 * 
 * 4) 필요한 로직 처리
 *     - 파라미터 얻어오기
 *     - 필요한 요청 처리 구문 작성
 *     
 * 5) 응답 형태 지정 + 응답 스트림 얻어오기
 * 
 * 6) 스트림을 통해서 응답 데이터(html 코드) 출력하기
 */

@WebServlet("/ex3")
public class ExampleServlet1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 얻어오기
		String pizza= req.getParameter("pizza"); //피자 얻어옴
		String type= req.getParameter("type"); //도우 얻어옴
		String ordererName= req.getParameter("ordererName"); //주문자명 얻어옴
		String ordererAddress= req.getParameter("ordererAddress"); //주소 얻어옴
		
		// String[ ] req.getParameterValues( "name속성값" )
		//-> 같은 name 속성 값을 가지는 값을 모두 모아
		//   하나의 String[ ]로 만들어 반환하는 메서드
		String[] options = req.getParameterValues("opt");
		// - 아무것도 선택하지 않으면 null
		// - 하나라도 선택하면 String[ ] 반환
		
		System.out.println("[서버] /ex3 요청됨");
		
		//-------------------------------------------------------------------------------------------------------------------------------
		
		//선택한 피자,  옵션에 따라 달라지는 가격 계산하기
		
		int price = 0;
		
		switch(pizza) {
		case "맥콘 베이컨" : price += 15900; break;
		case "클래식 리코타" :     price += 16900; break;
		case "마라 불고기" :     
		case "맵퍼로니" :     price += 17900; break;
		}
		
		// 도우가 씬인 경우
		if(type.equals("thin")) price += 2000;
			
		//선택된 옵션이 있을 경우
		if(options !=null) {
			for(String opt : options) { //순차(반복) 접근
			 
				 switch(opt) {
				 
				 case "콜라" : price +=2000; break;
				 case "파스타" : price +=9800; break;
				 case "핫윙" : price +=9800; break;
				 
				 
				 }
			}
	}

		// 응답 형태 지정
		resp.setContentType("text/html; charset=utf-8");
		
		// 응답 스트림 얻어오기
		PrintWriter out = resp.getWriter( );
		
		StringBuilder sb= new StringBuilder();
		
		sb.append("<!DOCTYPE html>");
		
		sb.append("<html>");
		
		sb.append("<head>");
		sb.append("<title>");
		
		sb.append(String.format("%s님 주문 영수증", ordererName));
		
		sb.append("</title>");

		sb.append("</headl>");
		
		sb.append("<body>");
		
		sb.append("<h3>");
		 sb.append("주문자명 : ");
		 sb.append(ordererName);
		sb.append("</h3>");
		
		sb.append(String.format("<h3>주소 : %s</h3>", ordererAddress));
		
		sb.append("<ul>");
		
		sb.append(String.format("<li>피자 : %s</li>", pizza));
		
		String temp = type.equals("original") ?  "오리지널" : "씬도우";
		
		sb.append(String.format("<li>피자 : %s</li>", temp));
		
		if(options !=null) {//선택한 옵션이 있을 경우
			
			sb.append("<li>");
			
			sb.append("선택한 옵션 : ");
			
			for(String opt : options) sb.append(opt + " ");
			
			sb.append("</li>");
			
		}
		
		
		sb.append("</ul>");
		
		sb.append(String.format("<h3>금액 : %d 원</h3>", price));
		
		sb.append("</body>");
		sb.append("</html>");
		

		out.print(sb.toString());
	}
	
}
	

