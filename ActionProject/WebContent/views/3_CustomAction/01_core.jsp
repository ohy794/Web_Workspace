<%@ page import="java.util.ArrayList, com.kh.model.vo.Person" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Core Library</h1>
	<pre>
		*변수 선언(&lt; c:set var="변수명" value="리터럴" scope="스코프영역지정(생략가능)" &gt;)
		- 변수를 선언하고 초기값을 대입해두는 기능을 제공
		- 해당 변수를 어떤 scope에 담아둘껀지 지정가능함(생략시 기본적으로 pageScope에 담김)
		=> 즉, 해당 scope영역에 setAttribute라는 메소드를 이용해서 key + value형태로 데이터를 담아놓는것과 동일한 형태다
		=> c:set을 통해 선언된 변수는 EL로 접근해서 사용가능
		
		*주의사항
		- 변수의 타입을 별도로 지정하지 않음
		- 반드시 해당 변수의 담아두고자 하는 초기값(value)속성을 무조건 추가시켜줘야함(선언과 동시에 초기화 해줘야함)
	</pre>

<c:set var="num1" value="10" /> <!-- pageScope에 담김 -->


<!-- requestScope에 담김 -->
<c:set var="num2" scope="request">
	<%if(1+1 ==2){ %>
		2
	<% }else{ %>
		20
	<% } %>
</c:set>

num1 변수값 : ${num1 } 또는 ${pageScope.num1 }<br>
num2 변수값 : ${num2 } 또는 ${requestScope.num2 }<br>

<c:set var="result" scope="session" value="${num1 + num2}"/>


result 변수값 : ${result }<br><br>

<!-- 
	변수명만 제시하면 공유범위가 가장 작은곳에서부터 큰곳으로 순차적으로 찾아지게됨. 티가나지 않지만 속도가 조금 느려질수가 있다
	따라서 스코프영역.변수명을 작성하는것을 권장한다.
 -->

<hr>

<pre>
* 변수 삭제 (&lt; c:remove var="제거하고자하는 변수명" scope="스코프영역지정(생략가능)"&gt;)
- 해당 변수를 scope영역에서 찾아서 제거하는 태그
- socpe지정 생략시 모든 scope에서 해당 변수를 다~ 찾아서 제거함
=> 즉, 해당 scope에 .removeAttribute라는 메서드를 이용해서 제거하는 것이라고 생각하면됨
</pre>

삭제전 : result : ${result } <br><br>

1) 특정 scope 지정해서 삭제하기<br>
<c:remove var="result" scope="request"/>
request에 있는 result변수 삭제 후 result : ${result }

2) 모든 scope에서 삭제시키기<br>
<c:remove var="result"/>
삭제 후 result : ${result } <br><br>

<hr>

<pre>
*변수(데이터) 출력 : (&lt; c:out value="출력하고자 하는 값" default="기본값(생략가능)" escapeXml="true/false(기본값:true, 생략가능)"&gt;)
- 데이터를 출력하고자 할때 사용하는태그
- 기본값 : value에 출력하고자 하는값이 없을경우 기본값으로 출력할 내용물을 기술(생략가능)
- escapeXml : 태그로써 해석해서 출력할지 여부(생략가능, 기본값:true)
</pre>

result : <c:out value="${result }"/> <br>
default 설정시 result값 : <c:out value="${result }" default="값이 없음"/> <br><br>


<!-- escapeXml -->
<c:set var="outTest" value="<b>출력테스트<br>"/>
<c:out value="${outTest }"/><br> <!-- escapeXml 생략시 기본값은 true == 태그 해석안됨(문자열로 취급함) -->
<c:out value="${outTest }" escapeXml="false"/><br>

<hr>

<h3>2. 조건문 - if (&lt; c:if test="조건" &gt;)</h3>


<pre>
- 자바의 단일 IF문과 비슷한 역할을 하는태그
- 조건식을 TEST속성의 속성값으로 작성(**** 조건식은 무조건 EL표현식으로 작성해야함 ****)
</pre>
${num1 }
${num2 }
${10 > 2 }
${ '10' > '2' }
${'10'*1 > '2'*1 }
${num1 gt num2 }
<br>
<c:if test="${num1*1 gt num2*1 }">
	<b>num1이 num2보다 큽니다</b>
</c:if>

<!-- num1 <= num2 -->
<c:if test="${num1 le num2 }"><br>
	<b>num1이 num2보다 작거나 같습니다</b>
</c:if>


<c:set var="str" value="안녕"/>

<br>

<c:if test="${str eq '안녕' }">
	 <mark>Hello World!</mark>
</c:if>


<c:if test="${str ne '안녕하세요' }"><br>
	<mark>안녕히가세요</mark>
</c:if>


<h3>3. 조건문 - choose(&lt; c:choose&gt; , &lt; c:when&gt; , &lt;c:otherwise&gt;)</h3>
<pre>
- JAVA의 IF-ELSE, IF-ELSE IF또는 SWITCH문과 비슷한 역할을 하는 태그
- 각 조건들을 c:choose태그의 하위요소로 c:when, c:otherwise를 통해서 작성
</pre>


<c:choose>
	
	<c:when test="${num2 eq 2 }">
		<b>num2의 값은 2와 일치합니다</b> <br>
	</c:when>
	
	<c:when test="${num1 eq 10 }">
		<b>num1은 10과 일치합니다</b> <br>
	</c:when>
	<c:otherwise>
		<b>num1은 10 혹은 20과 일치하지 않습니다</b> <br>
	</c:otherwise>

</c:choose>


<hr>

<h3>4. 반복문</h3>
<pre>
for loop문  - (&lt; c:forEach var="변수명" begin="초기값" end="끝값" step="증가값(생략가능)" &gt;)
항샹된 반복문 - (&lt; c:forEach var="변수명" items="순차적으로 접근할 배열, 컬렉션" varStatus="현재 접근된 요소의 상태값을 저장하는 변수명(생략가능)"&gt;)
=> step : 지정하지 않을시 기본값은 1
=> varStatus : 현재 접근된 요소의 상태값을 보관할 변수명(index, count, first, last)
</pre>

<!-- for loop문 -->

<% for(int i = 1; i<= 10; i++) {  %>


<% } %>
<c:forEach var="i" begin="1" end="10">
	반복 확인 : ${i }<br>
	
</c:forEach>
<% for(int i = 1; i<= 10; i+=2) {  %>
<c:forEach var="i" begin="1" end="10" step="2">
	반복 확인 : ${i }<br>
	
</c:forEach>

<% } %>

<c:forEach var="i" begin="1" end="5">
	<input type="text" id="uni${i }" >
	<h${i }>${i }</h&{i}>
</c:forEach>

<%
	ArrayList<Person> list = new ArrayList<>();
	list.add(new Person("민경민", "남" , "무직"));
	list.add(new Person("경민", "남" , "알바"));
	list.add(new Person("민", "남" , "바리스타"));
	
	request.setAttribute("list", list);
%>


<table>
	<thead>
		<tr>
			<th>순번</th>
			<th>이름</th>
			<th>성별</th>
			<th>직업</th>
		</tr>
	</thead>
	<% if(!list.isEmpty()){ %>
		<% for(Person p : list) { %>
		<% } %>
	<%} %>
	<c:choose>
		<c:when test="${empty list }">
			<tr>
				<td colspan="4">조회된 결과가 없습니다</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="p" items="${requestScope.list }" varStatus="status">
				<tr align="center">
					<td>${status.count }</td> <!-- count : 1부터 시작하는값, index : 0부터 시작 -->
					<td>${list[status.index].name}</td>
					<td>${p.gender }</td>
					<td>${p.job }</td>
				</tr>
			</c:forEach>
			ㅅ
			<!-- 기본 반복문으로 컬렉션 배열 반복시키기 -->
			<c:forEach var="i" begin="0" end="<%= list.size() -1 %>">
				<tr align="center">
					<td>${i+1}</td> <!-- count : 1부터 시작하는값, index : 0부터 시작 -->
					<td>${list[i].name}</td>
					<td>${list[i].gender }</td>
					<td>${list[i].job }</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
</table>







</body>
</html>