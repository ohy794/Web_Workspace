<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer table{
		border-color : white;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">일반게시판 상세보기</h2>
		<br>
		
		<table id="detail-area" align="center" border="1">
			<tr>
				<th width="70">카테고리</th>
				<th width="70">운동</th>
				<th width="70">제목</th>
				<th width="350">제목부분입니다</th>
			</tr>
			<tr>
				<th>작성자</th>
				<th>user01</th>
				<th>작성일</th>
				<td>2023-02-20</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height:200px">글내용글내용</p>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					첨부파일이 없을경우 : 첨부파일 없음
					첨부파일이 있을경우
					<a download="파일원본명" href="해당파일이 존재하는경로">파일원본명</a>
				</td>
			</tr>
			
		</table>
		<br>
		<div align="center">
			<a href="<%=contextPath %>/list.bo?currentPage=1" class="btn btn=secondary btn-sm">목록가기</a>
			
			<!-- 로그인한 사용자가 해당 게실의 작성자인경구 -->
		<%-- 	<% if(loginUser) != null && loginUser.getUserId().equals(b.getBoardWriter()){ %>
				<a href="<%= contextPath %>/updateForm.bo?bno=<%=b.getBoardNo() %>" class="btn btn-warning btn=sm">수정하기</a>
				<a href="<%= contextPath %>/delete.bo?bno=<%=b.getBoardNo() %>" class="btn btn-danger btn=sm">수정하기</a>
			<%} %> --%>
		</div>
		
		<br>
		
		<!-- 댓글기능 화면구현만 해두기, 요 아래 작업은 x -->
		
		<div id="reply-area">
			<table border="1" align="center">
				<thead>
					<%if(loginUser != null){ %>
						<!-- 로그인이 되어있을경우 -->
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="replyContent" rows="3" cols="50" style="resize:none;"></textarea>
							</td>
							<td><button onclick="insertReplay();">댓글등록</button></td>
						</tr>
					<% }else{ %>
					<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="replyContent" rows="3" cols="50" style="resize:none;" readonly>로그인 후 이용가능한서비스입니다</textarea>
							</td>
							<td><button disabled>댓글등록</button></td>
						</tr>
					<%} %>
				</thead>
				<tbody>
				<tr>
					<td>user01</td>
					<td>테스트댓글</td>
					<td>2023-02-20</td>
				</tr>
				<tr>
					<td>user02</td>
					<td>테스트댓글</td>
					<td>2023-02-20</td>
				</tr>
				<tr>
					<td>user03</td>
					<td>테스트댓글</td>
					<td>2023-02-20</td>
				</tr>
				<tr>
					<td>user04</td>
					<td>테스트댓글</td>
					<td>2023-02-20</td>
				</tr>
				
				</tbody>
			</table>
		</div>
	</div>








</body>
</html>