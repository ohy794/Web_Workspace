package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestOrder
 */
@WebServlet("/RequestOrder.java")
public class RequestOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1) 전달값중에 한글이 있을경우 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		//2) 요청시 전달값 뽑기 및 데이터 가공처리 => 변수, 객체로 기록
		
		String pizza = request.getParameter("pizza");
		String[] toppings = request.getParameterValues("topping");
		String[] sides = request.getParameterValues("side");
		
		int price=0;
		
		switch(pizza) {
		case "콤비네이션피자" : price +=5000; break;
		case "치즈피자" : price += 6000; break;
		case "포테이토피자" :
		case "고구마피자" : price += 7000; break;
		case "불고기파자" : price += 8000; break;
		
		}
		
		if(toppings != null) {
			for(String topping : toppings) {
			switch(topping) {
			case "고구마무스" : price += 1000; break;
			case "콘크림무스" : price += 1500; break;
			case "파인애플토핑" :
			case "치츠토핑" : 
			case "치즈바이트" : price += 2000; break;
			case "치즈크러스트":
			}
		}
		}
		if(sides != null) {
			for(String side : sides) {
			
				price+=5000;
			
			}
		}
	 //4) 요청처리 후 사용자가 보게 될 응답페이지 만들기
		request.setAttribute("pizza", pizza);
		request.setAttribute("topping", toppings);
		request.setAttribute("side", sides);
		request.setAttribute("price", price);
		// 응답할 뷰를 선택
		RequestDispatcher view = request.getRequestDispatcher("/views/Order.jsp");
		view.forward(request, response);
	}
	
}

