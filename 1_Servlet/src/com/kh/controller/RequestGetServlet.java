package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("잘실행됨?");
		/*
		 * Get방식으로 요청했기 때문에 doGet메소드가 호출됨
		 * 
		 * 첫번째 매개변수인 HttpServletRequest request 에는 요청시 전달된 내용들이 담김(사용자가 입력한 값,
		 * 전송방식, 요청한 사용자의 ip등등
		 * 
		 * 두번째 매개변수인 HttpServletResponse response 에는 요청 처리후 응답할때 사용하는 객체
		 * 
		 * 요청을 처리하기 위해 요청시 전달된 값(사용자가 입력한 값)들을 뽑는다
		 * request의 parameter영역안에 존재 => key-value세트로 담겨있다(name속성값=value값)
		 * 
		 * request의 parameter영역으로부터 데이터를 뽑는 메소드
		 * -request.getParameter("키값") : String(그에 해당하는 value값) -> 무조건 문자열로 반환됨
		 * -request.getParameterValues("키값") : String[](그에 해당하는 value값)
		 * => 하나의 key값으로 여러개의 value값을 받는경우(체크박스) 문자열 배열형으로 반환가능
		 */
		String name = request.getParameter("name");//텍스트박스가 비어있는경우 빈 문자열("")로 넘어온다
		String gender = request.getParameter("gender");//라디오버튼의 경우 체크된 값이 없을때 빈 문자열이아닌 null이 넘어간다
		int age = Integer.parseInt(request.getParameter("age")); //반환값이 무조건 문자열이기때문에 형변환 해줘야함
		
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));// "160" -> 160.0
		
		//체크박스처럼 복수개의 정보를 받을때는 배열형태로 받아야함
		String[] foods = request.getParameterValues("food");//["한식", "중식" , "일식"...]// 체크한값이 없다면 null이넘어간다
		
		if(foods == null) {
			System.out.println("foods : 없음");
		}else {
			System.out.println("foods : "+String.join(",", foods)); //foods.join(",");
			//배열에 있는 모든 값들을 구분자를 통해서 하나의 문자열로 반환해주는 메소드
		}
		
		// 뽑아낸 값들을 가지고 요청 처리해야한다 (DB와 상호작용)
		// 일반적인 흐름 : Servlet -> Service(뽑아낸 값들을 넘기면서 비지니스로직 실행) -> Dao호출 - DB SQL문 실행 - 결과값 반환
		
		// 위의 과정을 다 처리한 후 사용자가 보게될 응답페이지를 만들어서 전달해주기
		
		/*
		 * 장점 : Java코드 내에 작성하기 때문에 반복문, 조건문, 유용한 메소드를 사용할수 있다.
		 * 
		 * 단점 : 복잡, 혹시라도 나중에 html수정해야할때 Java코드내에서 수정이 이루어지기때문에
		 * 		 수정된 내용을 다시 반영시키고자 한다면 서버를 재실행해야한다. 사소한 오타로 에러날수 있다.
		 */
		
		
		//* response 객체를 통해 사용자에게 HTML(응답화며)을 전달
		// 1)이제부터 내가 출력할 내용은 문서형태이고 HTML이다. 더불어서 문자셋은 utf-8이라는것을 지정
		response.setContentType("text/html; charset=UTF-8");
		
		// 2)응답하고자 하는 사용자(클라이언트)와의 스트림(클라이언트와의 통로)을 생성
		PrintWriter out = response.getWriter();
		
		// 3) 생성된 스트림을 통해 응답 html구문을 한줄씩 출력
		out.println("<html>");
			out.println("<head>");
				out.println("<style>");
				out.println("h2{color : red}");
				out.println("#name {color : orange}");
				out.println("#age {color : blue}");
				out.println("#city {color : yellow}");
				out.println("#height {color : gray}");
				out.println("#gender {color : green}");
				out.println("li {color : purple}");
				out.println("</style>");
			out.println("</head>");
			out.println("<body>");
				out.println("<h2>개인정보 응답 화면</h2>");
				
				out.println("<span id='name'>"+name+"</span>님은");
				out.printf("<span id='age'>%d</span> 살이며", age);
				out.printf("<span id='city'>%s</span>에 사는", city);
				out.printf("<span id='height>%.1f</span>cm 이고", height);
				
				out.print("성별은 ");
				if(gender == null){
					out.print("<span id='gender'>선택을 안했습니다</span><br>");
				}else {
					out.print("<span id='gender'>"+(gender.equals("M")? "남자" : "여자")+"</span>입니다<br>");
				}
				
				out.print("좋아하는 음식은 ");
				if(foods == null) {
					out.print("없습니다");
				}else {
					out.print("<ul>");
						for(int i=0; i<foods.length; i++) {
							out.printf("<li>%s</li>", foods[i]);
						}
						// out.printf("<li>"+String.join(",",foods)+"</li>");
					out.print("</ul>");
				}
				
				
			out.println("</body>");
		out.println("</html>");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
