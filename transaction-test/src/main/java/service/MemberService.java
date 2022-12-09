package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.MemberDao;
import dao.OutidDao;
import vo.Member;

public class MemberService {

	private OutidDao outidDao;
	private MemberDao memberDao;
	
	public int insertMember(Member paramMember) {
		int result = 0;
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58","root","java1234");
			conn.setAutoCommit(false);  // 자동커밋(execute()) 해지
			
			
			this.memberDao = new MemberDao();
			result = memberDao.insertMember(conn, paramMember);	
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}			
		
		return result;
	}
	
	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58","root","java1234");
			conn.setAutoCommit(false);  // 자동커밋(execute()) 해지
			
			this.outidDao = new OutidDao();

			if(outidDao.insertMemberId(conn,memberId) ==1) {
				this.memberDao = new MemberDao();
				this.memberDao.deleteMember(conn,memberId);
			}	
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}	
		return result;  // 탈퇴성공 1, 실패 0
	}
	
	public Member login(Member paramMember) {
		Member resultMember = null;
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58","root","java1234");
			conn.setAutoCommit(false);  // 자동커밋(execute()) 해지
			
			this.memberDao = new MemberDao();
			resultMember = memberDao.login(conn, paramMember);
		}catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}			
		return resultMember;
	}

}
