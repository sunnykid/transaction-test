package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OutidDao {

	//탈퇴아이디 입력
	
	public int insertMemberId(Connection conn,String memberId) throws Exception{
				
		int row = 0;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO outid(member_id,createdate) VALUES(?,NOW())";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		row = stmt.executeUpdate();  
        stmt.close();
		
		return row;  // 성공시 1 리턴
	}
}
