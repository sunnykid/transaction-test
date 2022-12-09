<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 유효성 검사 : 이 페이지 코드를 계속적인 실행 여부
	if(session.getAttribute("loginMember") != null){
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}
%>
<!-- View -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="<%=request.getContextPath()%>/loginAction.jsp" method="post">
		<table border="1">
			<tr>
				<td>ID</td>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">로그인</button>
		<a href="<%=request.getContextPath()%>/insertMemberForm.jsp">회원가입</a>
	</form>
</body>
</html>