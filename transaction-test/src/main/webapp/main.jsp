<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<%
	// 유효성 검사 : 이 페이지 코드를 계속적인 실행 여부
	// 로그인 되어있지 않다면 이 페이지는 실행안됨.
	if(session.getAttribute("loginMember") == null){
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
		return;
	}

	Object obj = session.getAttribute("loginMember");
	Member loginMember = (Member)obj;
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div><%=loginMember.getMemberName()%>님 반갑습니다.</div>
	<div><a href="<%=request.getContextPath()%>/logout.jsp">로그아웃</a></div>
	<div><a href="<%=request.getContextPath()%>/deleteMember.jsp">회원탈퇴</a></div>
</body>
</html>