package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/find")
public class FindServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/forward/find.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 전달 받은 파라미터 얻어오기
		String findName = req.getParameter("findName");
		
	List<String> nameList = new ArrayList<String>();
		// 한번에 import하는 법 ctrl + shift + o
	
		nameList.add("김길동");
		nameList.add("나길동");
		nameList.add("최길동");
		nameList.add("홍길동");
		nameList.add("고길동");
		nameList.add("우길동");
		nameList.add("박길동");
		
		// 입력된 이름이 List에 있다면 몇 번 인덱스에 있는지
		// 없으면 존재하지 않는다고 메시지를 만들어서
		// "/forward/result.jsp" 로 요청 위임
		
		String message = null;
		
		//findName과 같은 값이 nameList에 포함되어 있는 경우 true
		if(nameList.contains(findName)) { //있음
			
			message = nameList.indexOf(findName) + "번째 인덱스에 있습니다";
			
		}else { //없음
			message = "존재하지 않습니다";
		}
		
		//request scope에 message를 추가
		req.setAttribute("message", message);
		
		//forward 구문 작성
		
		String path = "/WEB-INF/views/forward/result.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
