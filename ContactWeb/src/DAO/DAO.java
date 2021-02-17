package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import VO.AccountVO;
import VO.ContactVO;

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
	public ArrayList<ContactVO> selectAll(String id) {
		ArrayList<ContactVO> member = new ArrayList<ContactVO>();
		query 						= new StringBuilder();
		dbCon 						= DBConnection.getInstance();
		query.append("select * 			");
		query.append("	from contact	");
		query.append("	where owner = ?	");
		try {
			con 	= dbCon.getConnection();
			pstmt 	= con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs 		= pstmt.executeQuery();
			while(rs.next()) {
				ContactVO contact = new ContactVO();
				contact.setName(rs.getString("name"));
				contact.setPhone(rs.getString("phone"));
				contact.setAddress(rs.getString("address"));
				contact.setCategory(rs.getString("category"));
				member.add(contact);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			dbCon.close(con, pstmt, rs);
		}
		
		return member;
	}
	public ContactVO selectByPhone(String id, String phone) {
		ContactVO contact = new ContactVO();
		dbCon = DBConnection.getInstance();
		query = new StringBuilder();
		query.append("select *			");
		query.append("  from contact	");
		query.append(" where phone = ?	");
		query.append("   and owner = ?	");
		
		try {
			con 	= dbCon.getConnection();
			pstmt 	= con.prepareStatement(query.toString());
			pstmt.setString(1, phone);
			pstmt.setString(2,  id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			contact.setName(rs.getString("name"));
			contact.setPhone(rs.getString("phone"));
			contact.setAddress(rs.getString("address"));
			contact.setCategory(rs.getString("category"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt, rs);
		}
		return contact;
	}
	public void update(String oldPhone, ContactVO contact) {
		dbCon = DBConnection.getInstance();
		query = new StringBuilder();
		query.append("update contact set	");
		query.append("   name = ?			");
		query.append(" , phone = ?			");
		query.append(" , address = ?		");
		query.append(" , category = ?		");
		query.append("  where phone = ?		");
		
		try {
			con = dbCon.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getPhone());
			pstmt.setString(3, contact.getAddress());
			pstmt.setString(4, contact.getCategory());
			pstmt.setString(5, oldPhone);
			int test = pstmt.executeUpdate();
			System.out.println(test);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbCon.close(con, pstmt);
		}
	}
	

}
