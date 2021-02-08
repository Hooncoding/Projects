package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import VO.AccountVO;

public class DAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder query = null;
	DBConnection dbCon = null;
	public AccountVO login(String id, String pw) {
		AccountVO account = new AccountVO(); 
		query = new StringBuilder();
		query.append("select *		");
		query.append("  from account	");
		query.append(" where id = ?	");
		query.append("   and pw = ?	");
		dbCon = DBConnection.getInstance();
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				account.setName(rs.getString("name"));
				account.setId(rs.getString("id"));							
			}
			System.out.println(account.getName());
			System.out.println(account.getId());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}	
		return account;
	}
	public void joinAccount(AccountVO account) {
		query = new StringBuilder();
		query.append("insert into account	"	);
		query.append("values(?,?,?,?,?)		"	);
		dbCon= DBConnection.getInstance();
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, account.getName());
			pstmt.setString(2, account.getId());
			pstmt.setString(3, account.getPw());
			pstmt.setString(4, account.getPhonenum());
			pstmt.setString(5, account.getGender());
			pstmt.executeUpdate();			
		}catch(Exception e) {
			
		}finally {
			dbCon.close(con, pstmt);
		}
		
		
		
		
	}

}
