<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 이구문을 스크립틀릿이라고 부르며, html문서내에 자바코드를 쓸 수 있는 영역
	// 현재 이 jsp에서 필요로 하는 데이터들 => request의 attribute영역에 담겨있음
	// request.getAttribute("key") : Object(그에 해당하는 벨류값의 자료형으로 형 변환해줘야함)
	String name    = (String) request.getAttribute("name"); 
	int age		   = (int) request.getAttribute("age");
	String gender  = (String) request.getAttribute("gender");
	String city    = (String) request.getAttribute("city");
	double height  = (double) request.getAttribute("height");
	String[] foods = (String[]) request.getAttribute("foods");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{color:red;}
	span{font-weight:bold;}
	#name{color:orange;}
	#age{color:yellow;}
	#city{color:green;}
	#gender{color:pink;}
	li{color:purple;} 
</style>
</head>
<body>
	
	<h2>개인정보 응답화면</h2>
	<span id='name'><%= name %></span> 님은
	<span id='age'><%= age %></span> 살이며
	<span id='city'><%= city %></span> 에살고
	<span id='height'><%= height %></span> cm 입니다
	
	성별은 
	<% if(gender == null){%>
		선택을 안했습니다.	
	<% } else{ %>
		<% if(gender.equals("M")){%>
			<span id='gender'>남자</span>입니다.<br>
		<% }else{%>
			<span id='gender'>여자</span>입니다.<br>
		<% } %>
		
	<% } %>
	
	좋아하는 음식은
	<% if(foods == null) {%>
		없습니다
	<%}else{ %>
		<ul>
			<% for(int i=0; i<foods.length; i++){ %>
				<li><%= foods[i] %></li>
			<% } %>
		</ul>
	<% } %>
</body>
</html>