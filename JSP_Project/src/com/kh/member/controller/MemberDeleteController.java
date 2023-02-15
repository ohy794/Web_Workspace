package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = ((Member) request.getSession().getAttribute("loginUser")).getUserId();	
		String userPwd = request.getParameter("userPwd");
		
		//로그인 한 회원의 정보를 얻어오는 방법
		// 방법1. session영역에 담겨있는 회원 객체로부터 뽑아내기
		// 방법2. input type="hidden" 회원정보를 숨겨서 요청시 함께 전달하기
		
		 int result = new MemberService().deleteMember(userId, userPwd);
		
		 HttpSession session = request.getSession();
		if(result > 0) {// 성공시 -> 메인페이지 alert, 로그아웃처리까지하기
			session.setAttribute("alertMsg", "회원 탈퇴를 성공했습니다");
			// 1.먼저 세션에 담긴데이터를 다 날린후에 alertMsg만 따로 ㅜㅊ가함
			// session.invalidate();
			
			// 세션에서 loginUser정보만 지워줌
			session.removeAttribute("loginUser");
			
			// 2. invalidate()메소드는 사용시 세션이 만료되어 안에들어간 데이터가 모두 날라감 -->
			//removeAttribute("loginUser") : 로그인한 사용자의 정보를 지워줌
			
			response.sendRedirect(request.getContextPath());
			// 3. 로그아웃 처리를 로그아웃서블릿에게 위임하는방법
			// response.sendRedirect(request.getContextPath() +"/myPage.me");
			
		}else {//실패시 --> 에러페이지로 포워딩 or 안내문구 전송
			session.setAttribute("alertMsg", "회원 탈퇴를 실패했습니다");
			
			response.sendRedirect(request.getContextPath() +"/myPage.me");
		}
		
		
		
	}
	
}
