package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클릭했을때의 공지사항 글번호 가져오기
		int nno = Integer.parseInt(request.getParameter("nno"));// "1" , "2"	, ""	
		/*
		 * SELECT *
		 * FROM NOTICE
		 * WHERE NOTICE_NO = ${nno}
		 */
		
		// 조회수 증가용 서비스
		/*
		 * UPDATE NOTICE SET
		 * COUNT = COUNT +1
		 * WHERE NOTICE_NO = ${nno}
		 *  */
		int result = new NoticeService().increaseCount(nno);
		// 조회수 증가에 성공했을때 -> 공지사항 상세조회후 noticeDetatilView로 포워딩
		if(result > 0) {
			
			Notice n = new NoticeService().selectNotice(nno);
			request.setAttribute("n", n);
			
			//response.sendRedirect("/jspproject"); request에 담겨있떤 데이터가 싹 날라간다.
			
			request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
			
		} else { // 조회수 증가 실패시 -> 에러페이지로 포워딩
			request.setAttribute("errorMsg", "공지사항 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
