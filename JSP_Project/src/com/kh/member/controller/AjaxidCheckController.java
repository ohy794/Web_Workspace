package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxidCheckController
 */
@WebServlet("/idCheck.me")
public class AjaxidCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxidCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자가 전달한 데이터 변수화
		String userId = request.getParameter("userId");
		// 2. db에 현재 전달된 데이터가 존재하는지 확인
		int count = new MemberService().idCheck(userId);
		// 3. 중복된 아이디가 존재하는 케이스, 존재하지 않는 케이스
		if(count > 0) {// 중복된 아이디가 존재하는 케이스
			response.getWriter().print("NNNNN");
		}else {//존재하지 아이디가 없는 케이스
			response.getWriter().print("NNNNY");
		}
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
