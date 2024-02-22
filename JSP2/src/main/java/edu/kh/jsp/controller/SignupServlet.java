package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{

	// a 태그 요청(get)처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//forward -> 요청에 따른 응답 화면이 존재함
		String path ="/WEB-INF/views/redirect/signup.jsp"; //응답해줄 jsp파일이 어디 있는지
		
		//요청 발송자 필요
		req.getRequestDispatcher(path).forward(req, resp);
		
		
	}
	
	// form 태그 제출 (POST) 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 얻어와 변수에 저장 (기본 String 타입)
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String pwCheck= req.getParameter("pwCheck");
		
		//pw, pwCheck가 일치하면 "(id) 회원 가입 성공!!"
		//일치하지 않으면 "비밀번호 불일치"
		//메시지를 message 변수에 저장
		
		String message = null;
		if(pw.equals(pwCheck)) {
			message = id + " 회원 가입 성공";
		}else {
			message = "비밀번호 불일치";
		}
	    
		//req.setAttribute("message", message); //안됨
		
		//-> redirect(재요청) 수행 시
		// 이전 요청이 담긴 request 객체가 삭제되고
		// 새로운 요청이 담긴 request 객체가 생성된다!!!
		
		// -> 이전 request에 message가 세팅되었기 때문에
		// 새로운 request에는 존재하지 않음 -> 출력 X
		
		// [해결 방법]
		// session 객체를 이용하면 해결 가능!!
		// 왜? session은 완료나 창이 닫히기 전까지 계속 유지됨
		//session은 redirect를 해도 
		//사라지거나 다시 생성되는 객체가 아니기 때문에 
		// (브라우저 종료 또는 만료 전까지 유지됨)
		
		HttpSession session = req.getSession(); //session 객체 얻어오기
		
		session.setAttribute("message", message); //session에 message 추가
		
		
		
		//Servlet 코드 수행이 완료된 후
		// - 원래 존재하던 페이지로 이동하거나
		// - 응답할 화면이 별도로 존재하지 않은 경우
		// *** redirect *** 수행 
		
		
		resp.sendRedirect("/signup"); // 원래 존재하던 페이지
		
		// - redirect는 무조건 GET 방식 요청이다
		
		
	}
}
