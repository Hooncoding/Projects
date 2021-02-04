package membDAO;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import membVO.MembVO;

	public class MembDAO {
	   
	//  DB 접속 method
	   private Connection getConnection() { // 메소드 호출 시 CONNECTION 객체 반환
		  String url 		= "jdbc:oracle:thin:@localhost:1521:xe"; // 접속할 정보: URL
	      String user    	= "ora_user"; // 접속할 정보: USER 아이디
	      String password	= "hong"; // 접속할 정보: 비밀번호
	      Connection con    = null; // 초기화 -> 초기화 안하면 TRY - CATCH 걸어야 함.
	      try {
	         con = DriverManager.getConnection(url, user, password); // DB 접속         
	      }catch(SQLException e) { // 예외 처리
	         e.printStackTrace();
	    	  System.out.println("접속 실패"); // 접속 실패 시 "접속 실패" 출력
	      }
	      return con; // Connection 객체 리턴
	   }
	//   close 메소드 (for select): select문 사용 후 접속 종료 시 발생할 수 있는 예외 처리를 위한 메소드
	   private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
	      try {
	         if(rs != null) { // ResultSet -> PreparedStatement -> Connection 순으로 close
	            rs.close();
	         }
	         if(pstmt != null) {
	            pstmt.close();
	         }
	         if(con != null) {
	            con.close();
	         }
	      }catch(SQLException e) { // 접속 해제 실패 시 "접속 해제" 출력
	         System.out.println("접속 해제 실패");
	      }
	   }
 //   close 메소드 (for insert, update, delete): 접속 종료 시 발생할 수 있는 예외 처리를 위한 메소드
	   private void close(Connection con, PreparedStatement pstmt) {
	      try {
	         if(pstmt != null) { // PreparedStatement -> Connection 순으로 close
	            pstmt.close();
	         }
	         if(con != null) {
	            con.close();
	         }
	      }catch(SQLException e) {
	         System.out.println("접속 해제 실패"); // 접속 해제 실패 시 "접속 해제" 출력
	      }
	   }
//	   1. DB 연동 하여 회원 추가
//	   1.1 이 메소드는 int를 반환
//	   		1.1.1 insert, update, delete의 결과는 pstmt.executeUpdate => 리턴값이 int.
//	   		1.1.2 DAO 클래스에서는 DB와 연동하는 작업만 수행 -> DB에 insert 하고 나온 결과값(삽입된 row 갯수)을 Service로 전달하는 역할까지만 한다.
//	   1.2 이 메소드는 MembVO를 파라미터로 받음
//	   DB에 입력할 회원의 정보는 MembVO 객체에 저장되어 있음 => DAO는 MebmVO객체를 전달받아 DB에 전달(연동, 트럭에 적재하여 보내기)하는 역할을 한다. 
	   public int insertMember(MembVO memb) { 
	      int rowcnt = 0; //pstmt.executeUpdate()를 저장할 int 변수 선언
	      Connection con = getConnection(); // 위에서 생성한 getConnection() 호출 => insertMember내 선언된 con에 대입 => DB 연결
	      PreparedStatement pstmt = null; // pstmt 초기화 => 실제 값 저장은 try-catch문 안에서.
	      StringBuilder sql = new StringBuilder(); // DB script에 쓰일 쿼리문을 저장할 StringBuilder 객체 sql 생성.
//	      SQL 문: StringBuilder.append()를 통해 sql에 저장된 스트링들은 => DB 스크립트에 쓰여진다. (Java - DB 연동)
	      sql.append("INSERT INTO CONTACT(MEMBNM  		  ");
	      sql.append("                  , MEMBNO    	  ");
	      sql.append("              	, MEMBAD    	  ");
	      sql.append("              	, MEMBTP)   	  ");
	      sql.append("          VALUES(?, ?, ?, ?)  	  "); // 물음표는 MembVO 내 변수들을 받을 것이다.
	      try {
	         pstmt = con.prepareStatement(sql.toString()); // DB script에 쓰일 sql을 pstmt에 적재. 이때 con.prepareStatement() 메소드의 파라미터는 String 이므로 
//	         												  toString을 꼭 해줘야한다.
	         pstmt.setString(1, memb.getMembnm());    // 1번 물음표: 이름(MevbVO 내 membnm)
	         pstmt.setString(2, memb.getMembno());    // 2번 물음표: 전화 번호 (MevbVO 내 membno)
	         pstmt.setString(3, memb.getMembad());    // 3번 물음표: 주소 (MevbVO 내 membad)
	         pstmt.setString(4, memb.getMembtp());    // 4번 물음표: 종류 (MevbVO 내 membtp)
	         rowcnt = pstmt.executeUpdate(); // sql문을 실제 DB 스크립트에 적재하고 실행 & 결과(변경된 row 수) 반환하여 rowcnt에 저장
	         
	      }catch(SQLException e) { // try 문 내에서 발생할 수 있는 SQLException들을 받는 catch
	         System.out.println("이미 존재하는 전화번호 입니다."); // MEMBNO는 PK이다. MEMBNO 컬럼에 중복된 값을 입력할 경우 해당 메세지 출력	
	      }finally {
	         close(con, pstmt); // close
	      }      
	      return rowcnt; // insert 결과 (insert 된 행의 갯수) 반환
	   }
	   
//	2. 회원 목록 보기
	   public ArrayList<MembVO> selectAll(){
		   Connection con = getConnection(); // DB에 연결
		   PreparedStatement pstmt = null; //
		   ResultSet rs = null;
		   ArrayList<MembVO> membList = new ArrayList<MembVO>(); // Select 결과는 ResultSet으로 나옴 => 자바에서 결과값 출력을 위해서는 ResultSet으로 부터 나온 Data를 VO에 저장하고 해당 VO를 출력하기 위해 ArrayList에 담는다

		   StringBuilder sql = new StringBuilder();
		   sql.append("SELECT MEMBNM			");
		   sql.append("		, MEMBNO			");
		   sql.append("		, MEMBAD			");
		   sql.append("		, MEMBTP			");
		   sql.append("  FROM CONTACT			"); // 모든 행 출력 select *로 해도 된다.
		 
		   try {
			   pstmt = con.prepareStatement(sql.toString()); // sql문 pstmt에 적재
			   rs = pstmt.executeQuery(); //pstmt의 실행 결과값(ResultSet)을 rs에 저장
			   while(rs.next()) { // ResultSet 안에 담긴 Data를 VO에 옮기기 위한 반복문
				   MembVO membVO = new MembVO();
				   membVO.setMembnm(rs.getString("MEMBNM")); // ResultSet의 1개 행 내 MEMBNM 컬럼에 있는 값을 가져다가 VO의 Membnm 변수(String)에 저장
				   membVO.setMembno(rs.getString("MEMBNO")); // ResultSet의 1개 행 내 MEMBNO 컬럼에 있는 값을 가져다가 VO의 Membno 변수(String)에 저장
				   membVO.setMembad(rs.getString("MEMBAD")); // ResultSet의 1개 행 내 MEMBAD 컬럼에 있는 값을 가져다가 VO의 Membad 변수(String)에 저장
				   membVO.setMembtp(rs.getString("MEMBTP")); // ResultSet의 1개 행 내 MEMBTP 컬럼에 있는 값을 가져다가 VO의 Membtp 변수(String)에 저장	
				   membList.add(membVO); //위  변수들을 가지고 있는 membVO클래스를 ArrayList에 저장
			   }
		   }catch(SQLException e) { //SQL 예외 처리
			   e.printStackTrace();
		   }finally {
			   close(con, pstmt, rs); // close for select 호출
		   }
		   
		   
		   return membList; // MembVO를 담고 있는 ArrayList 반환
	   }
//	3. 회원 검색
	  public ArrayList<MembVO> searchMember(String membnm){ //membnm에 해당하는 이름을 가진 회원을 수정하는 메소드
		  ArrayList<MembVO> membList = new ArrayList<MembVO>(); // 전체 회원정보(MembVO)를 담을 ArrayList New
		  ArrayList<MembVO> membSearchList = new ArrayList<MembVO>(); // 검색 된 회원 정보(MembVO)를 담을 ArrayList New
		  membList = selectAll(); // selectAll 호출하여 전체 회원정보 ArrayList에 저장
		  
		  for(int i = 0; i < membList.size(); i++) {
			  if(membList.get(i).getMembnm().equals(membnm) == true) { // 전체 회원정보(MembVO) 중 membnm과 같은 이름(MembVO.Mebmnm)을 가진 MembVO만 MSL에 저장
				  membSearchList.add(membList.get(i));
			  }
		  }
		  return membSearchList; // 검색된 회원의 정보만 리턴
	  }
//	  4. 회원 삭제
	  public int deleteMember(String membnm, int num) {
		  int rowcnt = 0;
		  ArrayList<MembVO> membSearchList = searchMember(membnm); // 검색한 회원 결과를 MSL에 저장
		  if(membSearchList.size() != 0) {
			  String membname = membSearchList.get(num).getMembnm(); // 검색한 회원 이름
			  Connection con = getConnection();
			  PreparedStatement pstmt = null;
			  StringBuilder sql = new StringBuilder();
			  sql.append("DELETE CONTACT		");
			  sql.append(" WHERE MEMBNO = ?	");
			  try {
				  pstmt = con.prepareStatement(sql.toString());
				  pstmt.setString(1, membSearchList.get(num).getMembno()); // 검색한 회원 목록 중 num번째 회원의 전화번호에 해당하는 행을 삭제
				  														   // where 절에 membnm을 할 경우 동명이인이 다 삭제된다. => PK인 번호로 조건 지정 해줘야함 
				  rowcnt = pstmt.executeUpdate(); //결과 (삭제된 행 갯수)를 rowcnt에 저장
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }finally {
				  close(con, pstmt);
			  }
		  }
		  return rowcnt;
	  }
//	 5. 회원 수정    
	 public int updateMember(MembVO memb, String membnm, int num) {
		 deleteMember(membnm, num); // 해당 이름과 목록 내 번호에 해당하는 기존 회원을 삭제
		 return insertMember(memb); // 입력 받은 정보로 새로 insert.
	 }
	}

