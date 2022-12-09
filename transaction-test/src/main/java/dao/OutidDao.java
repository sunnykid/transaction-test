package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OutidDao {

	//탈퇴아이디 입력
	
	public int insertMemberId(String memberId) {
				
		int row = 0;
		Connection conn = null;
//		PreparedStatement outIdStmt = null;
//		PreparedStatement memberStmt = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db58","root","java1234");
			// 1)
			String sql = "INSERT INTO outid(member_id,createdate) VALUES(?,NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			row = stmt.executeUpdate();  //autocommit : false
			
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();  // 1),2)중에 하나라도 실패시 롤백
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return row;  // 성공시 2 리턴
	}
}
