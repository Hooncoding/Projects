package membService;

import java.util.ArrayList;

import membDAO.MembDAO;
import membVO.MembVO;

public class MembService {
//	   1. 회원 조회 Service
	   public ArrayList<MembVO> selectAll() {
		   ArrayList<MembVO> membList = null;
		   MembDAO membDao = new MembDAO();
		   membList = membDao.selectAll(); //membDao에서 나온 결과값(MembVO 저장한 ArrayList)을 로컬 ArrayList에 저장
		   return membList; // Controller에게 전달
	   }
	   public ArrayList<MembVO> searchMember(String membnm){
		   ArrayList<MembVO> membSearchList = null;
		   MembDAO membDao = new MembDAO();
		   membSearchList = membDao.searchMember(membnm); //membDao에서 나온 결과값(MembVO 저장한 ArrayList)을 로컬 ArrayList에 저장
		   return membSearchList; // Controller에게 전달
	   }
	   
	   public int insertMember(MembVO memb) {
		   int rowcnt = 0;
		   MembDAO membDao = new MembDAO();
		   rowcnt = membDao.insertMember(memb); //membDao에서 나온 결과값(변경된 row 수)을 int 변수에 저장      
		   return rowcnt; // Controller에게 전달
	   }
	   
	   public int updateMember(MembVO memb, String membnm, int num) {
		   int rowcnt = 0;
		   MembDAO membDao = new MembDAO();
		   rowcnt = membDao.updateMember(memb, membnm, num); //membDao에서 나온 결과값(변경된 row 수)을 int 변수에 저장
		   return rowcnt; // Controller에게 전달
	   }
	   
	   public int deleteMember(String membnm, int num) {
		   int rowcnt = 0;
		   MembDAO membDao = new MembDAO();
		   rowcnt = membDao.deleteMember(membnm, num); //membDao에서 나온 결과값(변경된 row 수)을 int 변수에 저장
		   return rowcnt; // Controller에게 전달
	   }
	}