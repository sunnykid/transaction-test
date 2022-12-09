package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao {
	// 아이디 중복검사
	// param String : 사용할 아이디
	// return boolean value : true(입력된 아이디 사용가능) / false(입력된 아이디 사용불가)
	public boolean checkMemberId(String memberId) {
		boolean result = true;  //default 값
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58","root","java1234");
			String sql = "SELECT t.id"
					   + " FROM (SELECT member_id id FROM member UNION SELECT member_id id FROM outid) t"
					   + " WHERE t.id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = false;  //이미 사용중인 아이디
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	//회원가입
	public int insertMember(Connection conn,Member member) throws Exception {
		int row = 0;
		PreparedStatement stmt = null;

		
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58","root","java1234");
		String sql = "INSERT INTO member(member_id,member_pw,member_name) VALUES (? , PASSWORD(?),?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		stmt.setString(3, member.getMemberName());
		row = stmt.executeUpdate();
			
		stmt.close();
		conn.close();

		return row;
	}
	
	//로그인
	@SuppressWarnings("null")
	public Member login(Connection conn,Member paramMember) throws Exception {
		Member resultMember = null;
		PreparedStatement stmt = null;
		
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58","root","java1234");
		String sql = "SELECT member_id memberId, member_name memberName FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			resultMember = new Member();
			resultMember.setMemberId(rs.getString("memberId"));
			resultMember.setMemberName(rs.getString("memberName"));
		}
		
		return resultMember; //로그인 실패시 null, 성공하면 Member객체
	}
	
		
	//회원탈퇴
	public int deleteMember(Connection conn, String memberId) throws Exception {

		int row = 0;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM member WHERE member_id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		row = stmt.executeUpdate(); 
		stmt.close();
		
		return row;  // 성공시 1 리턴
	}
}
