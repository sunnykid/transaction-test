<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<!-- View 없다 -->

<%
	// 로그인되어있는 사용자는 출입금지
	
	// 유효성 검사 : 이 페이지 코드를 계속적인 실행 여부
	// 로그인 되어있지 않다면 이 페이지는 실행안됨.
	if(session.getAttribute("loginMember") != null){
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}	

	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	Member paramMember = new Member();
	paramMember.setMemberId(memberId);
	paramMember.setMemberPw(memberPw);
	
	MemberDao memberDao = new MemberDao();
	Member member = memberDao.login(paramMember);
	
	if(member == null){
		//로그인 실패
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
	}else{
		//로그인 성공
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath()+"/main.jsp");
	}
	
	
	
	
%>