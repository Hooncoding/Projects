package membView;

import java.util.ArrayList;

import membService.MembService;
import membVO.MembVO;

public class MembView {
// 1. 전체 메뉴 관련 출력 모음
// 1.1 전체 메뉴 출력
   public void printmenu() {
      System.out.println("===========================");
      System.out.println("다음 메뉴 중 하나를 선택하세요.");
      System.out.println("===========================");
      System.out.println("1. 회원 추가");
      System.out.println("2. 회원 목록 보기");
      System.out.println("3. 회원 정보 수정하기");
      System.out.println("4. 회원 삭제");
      System.out.println("5. 종료");      
   }
// 1.2 경고 문구 출력 : 메뉴 선택(정수)시 잘못된 값 입력될 경우  
   public void printWrongMenu() { // 0 이나 6 이상의 값 입력 시
	   System.out.println("1~5까지의 정수만 입력하세요");
   }
 
   public void printWrongMenu2() { // 정수가 아닌 값 입력 시
	   System.out.println("숫자만 입력하세요");
   }
   
// 2. Insert into 관련 출력 모음
// 2.1 이름을 입력받기 위한 문구 출력
   public void printInsertName() {
	   System.out.println("등록할 회원의 정보를 입력하세요");
	   System.out.print("이름: ");
   }
// 2.2 전화번호를 입력받기 위한 문구 출력
   public void printInsertNumber() {
	   System.out.print("전화번호(ex.01012345678): ");
   }
// 2.2.1 경고 문구 출력: 숫자가 아니거나 11자리가 아닌 숫자 입력시
   public void printWrongNumber() {
	   System.out.print("잘못된 전화번호 입니다.");
   }
// 2.3 주소를 입력받기 위한 문구 출력
   public void printInsertAddress() {
	   System.out.print("주소: ");
   }
// 2.4 종류를 입력받기 위한 문구 출력
   public void printInsertType() {
	   System.out.print("회원 종류(가족, 친구 , 기타): ");
   }
// 2.4.1 경고 문구 출력: 가족, 친구, 기타가 아닌 회원 종류 입력 시
   public void printWrongType() {
	   System.out.println("잘못된 회원 종류 입니다.");
   }
// 2.5 정상 처리 문구 출력: insert 성공 시    
   public void insertResultSuccess() {
	   System.out.println("회원 추가 완료");
   }
// 2.6 경고 문구 출력: insert 실패 시
   public void insertResultError() {
	   System.out.println("회원 추가 실패");
   }
   
// 3. Select 관련 출력 모음 
// 3.1 모든 회원 정보 출력
   public void printSelectAllResult(ArrayList<MembVO> memblist) {
	   for(int i = 0; i < memblist.size(); i++) {
		   System.out.println(memblist.get(i));
	   }
   }
      
// 4. 검색 관련 출력 모음
// 4.1 검색할 이름을 입력 받기 위한 문구 출력  
   public void printSearchName() {
	   System.out.print("검색할 이름을 입력하세요: ");
   }
// 4.2 검색 결과(회원 정보) 출력
   public void SearchResultSuccess(ArrayList<MembVO> searchmember) {
	   	   System.out.println("================================ 검색 결과 ================================");
		   for(int i = 0; i < searchmember.size(); i++) {
			   System.out.println(i+1+ ". " + searchmember.get(i));
		   }
   }
// 4.3 경고 문구 출력: 검색된 회원이 없을 시
   public void SearchResultError(ArrayList<MembVO> searchmember) {
		   System.out.println("해당 이름으로 검색된 회원이 없습니다");
   }
   
// 5. Update 관련 출력 모음
// 5.1 목록 중 수정할 번호를 입력 받기 위한 문구 출력  
   public void UpdateNumber() {
	   System.out.print("수정할 번호를 입력하세요: ");
   }
// 5.2 경고 문구 출력: 입력 받은 숫자가 숫자가 아니거나 목록에 없는 숫자일 시  
   public void WrongsearchNumber() {
	   System.out.println("잘못된 번호 입니다");
   }
// 5.3 수정 받을 회원의 이름을 입력 받기 위한 문구 출력
   public void printUpdateName() {
	   System.out.println("수정할 회원의 정보를 입력하세요");
	   System.out.print("이름: ");
   }

// 5.4 정상 처리 문구 출력: Update 성공 시  
   public void UpdateResultSuccess() {
	   System.out.println("회원 수정 완료");
   }
// 5.5 경고 문구 출력: Update 실패 시  
   public void UpdateResultError() {
	   System.out.println("회원 수정 실패");
   }
// 6. Delete 관련 출력 모음
// 6.1 목록 중 삭제할 회원의 번호를 입력 받기 위한 문구 출력
   public void printDeleteNumber() {
	   System.out.print("삭제할 번호를 입력하세요: ");
   }
// 6.2 정상 처리 문구 출력: Delete 성공 시  
   public void DeleteResultSuccess() {
	   System.out.println("회원 삭제 완료");
   }
// 6.3 경고 문구 출력: Delete 실패 시  
   public void DeleteResultError() {
	   System.out.println("회원 삭제 실패");
   }
// 7. 종료 관련 출력 모음
// 7.1 프로그램 종료 문구 출력  
   public void printExit() {
	   System.out.println("프로그램을 종료합니다");
   }
}