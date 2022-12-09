<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<!-- View 필요없다 -->

<%
	// 로그인되어있는 사용자는 출입금지
	
	// 유효성 검사 : 이 페이지 코드를 계속적인 실행 여부
	// 로그인 되어있지 않다면 이 페이지는 실행안됨.
	if(session.getAttribute("loginMember") == null){
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}

	Member loginMember = (Member)(session.getAttribute("loginMember"));
	
	MemberDao memberDao = new MemberDao();
	// 2. 탈퇴성공
	int row = memberDao.deleteMember(loginMember.getMemberId());
    if(row == 2) { // 탈퇴성공
 	      System.out.println("탈퇴성공!");
 	      session.invalidate();
	      response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
    } else { // 탈퇴실패
	      System.out.println("탈퇴실패!");
	      response.sendRedirect(request.getContextPath()+"/main.jsp");
    }
	
%>