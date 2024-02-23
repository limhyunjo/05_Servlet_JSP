package edu.kh.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import edu.kh.test.model.dto.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/join") 
public class JoinServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * 1. 입력 받은 아이디가 List에 없다면 List에 추가 후 "/WEB-INF/views/success.jsp"로 forward 수행.
		 * forward 시 "(아이디)/(이름) 님이 가입 되었습니다 (비밀번호 : OOOO)" 메시지도 전달하여 응답 화면에서 출력
		 * 
		 * 2. 입력 받은 아이디가 List에 있다면 "/" 로 redirect 수행. redirect 시
		 * "(아이디)은/는 이미 존재하는 아이디 입니다." 메시지도 전달하여 응답 화면에서 출력
		 */
		

		
		List<Member> memberList = new ArrayList<Member>();
		memberList.add(new Member("test01", "1234", "김테스트"));
		memberList.add(new Member("test02", "5678", "최테스트"));
		memberList.add(new Member("test03", "qwer", "박테스트"));
		//-----------------------------------------------------------
	
		
		String memberId = req.getParameter("memberId"); 
		String memberPw = req.getParameter("memberPw");
		String memberName = req.getParameter("memberName");
	   
		
		for(Member member : memberList  ) {
			if(member.getMemberId().equals(memberId)) {
				HttpSession session = req.getSession();
				
				session.setAttribute("message", memberId + "은/는 이미 존재하는 아이디 입니다.");
				resp.sendRedirect("/");
				return;
			}
		}
		
		
		Member member = new Member(memberId, memberPw, memberName);
		memberList.add(member);
		String message =
				String.format("%s/%s 님이 가입 되었습니다 (비밀번호 : %s)"  , memberId, memberName, memberPw);
		req.setAttribute("message", message);
		
		String path = "/WEB-INF/views/success.jsp"; 
		req.getRequestDispatcher(path).forward(req, resp);
		

	}
}
