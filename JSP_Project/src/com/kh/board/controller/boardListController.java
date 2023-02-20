package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class boardListController
 */
@WebServlet("/list.bo")
public class boardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이징 처리
		 int listCount; // 현재 게시판의 총개시글수
		 int currentPage; // 현재 페이지(사용자가 요청한페이지)
		 int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		 int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수
		 
		 int maxPage; // 가장 마지막 페이지가 몇번 페이지인지 (총 페이지 수 )
		 int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		 int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
		
		 // * listCount : 총 게시글 갯수 --> Board 테이블안에 저장되어있는 행의 갯수
		 listCount = new BoardService().selectListCount(); // 124
		 
		 // * currentPage : 현재페이지(사용자가 요청한 페이지)
		 currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
		 
		 // *pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수(페이지 목록들 몇개단위로 출력할건지)
		 pageLimit = 10;
		 
		 // * boardLimit : 한페이지에 보여질 게시글의 최대 갯수(게시글 몇개 단위씪)
		 boardLimit = 10;
		 
		 // * maxPage : 가장 마지막 페이지가 몇번 페이지인지 (총 페이지 수)
		 /*
		  * listCount(게시글갯수와)와 boardLimit(한페이지에 보여질 게시글수)에 영향을 받음
		  * 
		  * - 공식 구하기
		  * boardLimit는 10이라는 가정하에 규칙을 세움
		  * 
		  * 총 갯수 					boardLimit						maxPage
		  * 100.0개						10			=> 100.0/10 ->		10
		  * 101.0개						10			=> 101.0/10 -> 		10.1 -> 11번페이지
		  * 105.0개						10			=> 105.0/10 -> 		10.5 -> 11번페이지
		  * 109.0개						10			=> 109.0/10 -> 		10.9 -> 11번페이지
		  * 110.0개						10			=> 110.0/10 -> 		11 -> 11번페이지
		  * 
		  * 111.0개						10			=> 111.0/10 -> 		11.1 -> 12번페이지
		  * 
		  * => 나눗셈한 연산결과를 무조건 올림처리하면 maxPage의 값이 나온다
		  * 
		  * 1) listCount를 double자료형으로 형변환
		  * 2) listCount / boardLimit
		  * 3) 결과를 올림처리 math.ceil() 메소드 호출
		  * 4) 결과값을 int자료형으로 형변환
		  */
		 maxPage = (int)Math.ceil(((double) listCount / boardLimit));
		 
		 
		 // * startPage : 페이지하단에 보여질 페이징바의 시작수 
		 /*
		  * currentPage와 pageLimit에 영향을 받음.
		  * - 공식 구하기
		  *	  pageLimit는 10이라는 가정하에 규칙을 구해보기
		  * 
		  *   startPage : 1, 11, 21, 31 ..=> n * 10 + 1 => 10의배수 + 1
		  *   만약에 pageLimit값이 5였다면 => 5의배수 + 1	
		  * 
		  *   즉 , n * pageLimt + 1
		  * 	
		  *	 currentPage							startPage
		  *			1									1					=> 0 * pageLimit+1  => n = 0
		  *			5									1					=> 0 * pageLimit+1  => n = 0
		  *			10									1					=> 0 * pageLimit+1  => n = 0
		  *
		  *			11									11					=> 0 * pageLimit+1  => n = 0
		  *			15									11					=> 0 * pageLimit+1  => n = 0
		  * 		20									11					=> 0 * pageLimit+1  => n = 0
		  *
		  *		currentPage의 값이 1~10 -> n = 0	
		  *						 11~20 -> n = 1
		  *						 21~30 -> n = 2
		  *						 n = (currentPage -1)/pageLimit
		  *							0~9 / 10 = 0
		  *							10~19 / 10 = 1
		  *							20~29 / 10 = 2
		  *		startPage = n * pageLimit +1;
		  *				  = (currentPage -1) / pageLimit * pageLimt +1
		  */
		 
		
		 startPage = (currentPage -1) / pageLimit * pageLimit +1;
		 
		 // * endPage : 페이지 하단에 보여질 페이징바의 끝수
		 /*
		  * startPage, pageLimt에 영향을 받음 + maxPage에도
		  * 
		  * - 공식구하기
		  *	  startPage : 1 -> endPage : 10
		  *   startPage : 11 -> endPage : 20
		  *   startPage : 21 -> endPage : 30
		  * 
		  *   endPage = startPage + pageLimit -1
		  */
		 endPage = startPage + pageLimit -1;
		 // starPage가 11이여서 endPage가 20으로 고정이되는데 실제로는 17페이지밖에 존재 안하는 경우?
		 // endPage를 17(maxpage)로 변경해줘야함
		 if(endPage > maxPage) {
			 endPage = maxPage;
		 }
		 
		 // 페이지정보들을 하나의 공간에 담아서 보내기
		 // 페이지 정보를 담을 vo클래스를 사용
		 // ->공지사항이나, 사진게시판 등 다양한 게시판에서 공통적으로 사용할 VO객체이기때문에 common패키지에 만듬
		 
		 //1. 페이징바 만들때 필요한 객체
		 PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		 
		 //2. 현재 사용자가 요청한 페이지(currentPage)에 보여질 게시글 리스트 요청하기
		 ArrayList<Board> list = new BoardService().selectList(pi);
		 
		 request.setAttribute("pi", pi);
		 request.setAttribute("list", list);
//		 System.out.println(pi);
//		 System.out.println(list);
		 
		 request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
