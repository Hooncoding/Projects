package membController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import membService.MembService;
import membVO.MembVO;
import membView.MembView;

public class MembController {

   public static void main(String[] args) {
      MembVO memb 			  = new MembVO(); // DB에 저장할 회원 정보를 저장하는 VO 객체 new
      MembView membView 	  = new MembView(); // View 객체 new (View 내 메소드 사용 목적)
      MembService membService = new MembService(); // MembService 객체 new (MembService 내 메소드 사용 목적)
      int selectMenu 		  = 0; //메뉴 선택 시 Scanner로 부터 선택할 정수를 받는 int 변수 선언
      Scanner scan 			  = new Scanner(System.in); // 메뉴 선택을 키보드로 부터 받기 위한 스캐너 생성
      while(true) {
    	  while(true) {
    	  membView.printmenu(); // 전체 메뉴 출력
    	  try {
    	  scan = new Scanner(System.in); // while문이 반복될때마다 Scanner 초기화 => 이걸 안해주면, 잘못된 값이 입력되었을 경우 무한 반복이 된다.	  
    	  selectMenu = scan.nextInt(); // 키보드로 입력 받은 정수 저장
    	  if(selectMenu >= 1 && selectMenu <= 5) { // 입력한 키가 1~5 사이 정수면
    		  break; //무한 반복 탈출
    	  }else {
    		  membView.printWrongMenu(); // 입력한 키가 1~5 사이 정수가 아니면 경고 문구 출력
    	  }
    	  }catch(InputMismatchException e) {
    		  membView.printWrongMenu2(); // 입력한 키가 정수가 아니면 경고 문구 출력
    	  }
    	  }
    	  
//    	  1. 회원 추가 
    	  if(selectMenu == 1) {
//    		  Scanner로 부터 입력받은 회원 정보를 저장할 String 변수명 선언
    		  String name;
    		  String number;
    		  String address;
    		  String type;
//    		  올바른 회원 정보를 받기 위한 반복문: 전화번호, 종류가 모두 제대로 입력되어야 탈출한다.
    		  while(true) {
    			  while(true) { // 전화번호가 제대로 입력되어야 탈출한다.
    				  ArrayList<Boolean> numcondition = new ArrayList<Boolean>();
    				  scan = new Scanner(System.in); // 스캐너 초기화: 메뉴 선택을 받는 스캐너 => 회원 정보를 받는 스캐너
    				  membView.printInsertName(); // 이름 입력 문구 출력
    				  name = scan.next(); // 키보드로 부터 입력받은 이름을 name 변수에 저장
    				  membView.printInsertNumber(); // 전화번호 입력 문구 출력
    				  number = scan.next(); // 키보드로 부터 입력받은 전화번호를 number 변수에 저장
    				  if(number.length() == 11) { // 전화번호의 길이가 11 글자이면 통과.
    					  for(int i = 0; i < number.length(); i++) { // 입력받은 전화번호 한글자 한글자가 0~9사이의 숫자 문자인지 확인하는 반복문
    						  										 // 11글자 중 숫자가 아닌 문자가 있으면 numcondition에 false가 저장된다
    						  if(number.charAt(i) >= 48 && number.charAt(i) <= 57) {
    							  numcondition.add(true);
    						  }
    					  }
    					  if(numcondition.contains(false) == false) {
    						  break; // numcondition 내 false가 하나라도 없으면 통과
    					  }					  
    				  }else {
    					  membView.printWrongNumber(); // 11글자가 아니거나 숫자 아닌 문자 있을 경우 경고 문구 출력
    				  }
    			  }
    			  membView.printInsertAddress(); // 주소 입력 문구 출력
    			  address = scan.next(); // 입력받은 주소를 address 변수에 저장
    			  membView.printInsertType(); // 종류 입력 문구 출력
    			  type = scan.next();// 입력받은 종류를 type 변수에 저장
    			  if(type.equals("가족") || type.equals("친구") || type.equals("기타")) {
    				  break; // 입력 받은 종류가 가족, 친구, 기타일 경우 통과.
    			  }
    			  else {
    				  membView.printWrongType(); // 입력 받은 종류가 가족, 친구, 기타가 아닐경우 경고문구 출력
    			  }
    		  } // Scanner 입력 완료
    		  
    		// Scanner로 입력 받은 정보 => String 변수에 저장 => MembVO 변수로 Set
    		  memb.setMembnm(name); 
    		  memb.setMembno(number);
    		  memb.setMembad(address);
    		  memb.setMembtp(type);
//    		  MembVO를 Service에 넘겨서 처리한 결과를 받아온다. 
//    		  결과: 삽입된 행 수 =>  0 이상이면 정상 입력 / 0 이면 입력 실패
    		  if(membService.insertMember(memb) > 0) {
    			  membView.insertResultSuccess(); // insert 성공 출력 			  
    		  }else {
    			  membView.insertResultError();   // insert 실패 출력
    		  }
    	  }
    	  
//    	 2. 회원 목록 보기
    	 if(selectMenu == 2) {
    		 membView.printSelectAllResult(membService.selectAll()); // Service에게 SelectAll을 요청하고, View를 통해 결과를 출력하게 하는 메소드 
    	 }
    	 
//    	 3. 회원 수정
    	 if(selectMenu == 3) {
    		 int num = 0; // 키보드로 부터 수정할 번호를 입력 받는 int 변수 선언
//    		 키보드로 부터 받은 회원 정보를 저장하기 위한 변수 set.
    		 String searchName;
    		 String name;
    		 String number;
    		 String address;
    		 String type;
  
    		 while(true) { // 검색한 이름이 나올때 까지 검색 받는 반복문
    			 membView.printSearchName(); // 검색할 이름을 입력하라는 문구 출력
    			 Scanner scannm = new Scanner(System.in); // 검색할 이름을 받을 Scanner 선언
    			 searchName = scannm.next(); // Scanner로 부터 입력 받은 결과값을 Searchname에 저장
    			 if(membService.searchMember(searchName).size() != 0) { // 검색한 이름이 DB에 있을 경우 (executeQuery한 결과값이 있을 경우)
    				 membView.SearchResultSuccess(membService.searchMember(searchName)); // executeQuery 한 결과값을 출력
    				 break;
    			 }else { // 검색한 이름이 DB에 없을 경우
    				 membView.SearchResultError(membService.searchMember(searchName)); // "이름 없음" 출력
    			 }
    			 
    		 }
    		 while(true) {
    			 try {
    				 membView.UpdateNumber();
    				 Scanner updatenum = new Scanner(System.in);
    				 num = updatenum.nextInt() - 1; 
    			 }catch(InputMismatchException e) {
    				 membView.printWrongMenu2(); // 입력한 키가 숫자가 아니면 경고 문구 출력
    			 }
    			 if(num > membService.searchMember(searchName).size()) {
    				 membView.WrongsearchNumber(); // 목록에 없는 번호를 입력할 경우 경고 문구 출력
    			 }else {
    			 break;
    			 }
    		 }
//    		 수정할 회원 정보 입력. (insert와 동일)
   		  	while(true) {
	   			  while(true) { 
	   				  ArrayList<Boolean> numcondition = new ArrayList<Boolean>();
	   				  scan = new Scanner(System.in);
	   				  membView.printUpdateName();
	   				  name = scan.next();
	   				  membView.printInsertNumber();
	   				  number = scan.next();
	   				  if(number.length() == 11) {
	   					  for(int i = 0; i < number.length(); i++) {
	   						  if(number.charAt(i) >= 48 && number.charAt(i) <= 57) {
	   							  numcondition.add(true);
	   						  }
	   					  }
	   					  if(numcondition.contains(false) == false) {
	   						  break;
	   					  }
	   					  
	   				  }else {
	   					  membView.printWrongNumber();
	   				  }
	   			  	}
	   			  membView.printInsertAddress();
	   			  address = scan.next();
	   			  membView.printInsertType();
	   			  type = scan.next();
	   			  if(type.equals("가족") || type.equals("친구") || type.equals("기타")) {
	   				  break;
	   			  }
	   			  else {
	   				  membView.printWrongType();
	   			  }
   			  
   		  	} // Scanner 입력 완료
    		
   		  	memb.setMembnm(name);
   		  	memb.setMembno(number);
   		  	memb.setMembad(address);
   		  	memb.setMembtp(type);
   		  	if(membService.updateMember(memb, searchName, num) > 0) { // Service에게 수정할 회원의 이름과 번호를 주고, 수정된 MembVO로 update할 것을 요청 
   		  		membView.UpdateResultSuccess(); // 수정 완료 시 수정 완료 문구 출력
   		  	}else {
   		  		membView.UpdateResultError(); // 수정 실패 시 수정 실패 문구 출력
   		  	}
    	 }
//    	 4. 회원 삭제
    	 if(selectMenu == 4) {
    		 String searchName; //삭제할 회원 이름을 입력 받는 변수 선언
    		 int num; // 검색한 회원 목록 중 삭제할 회원 번호를 입력 받는 변수 선언
    		 while(true) {
    			 membView.printSearchName(); // 검색 문구 출력
    			 Scanner scannm = new Scanner(System.in); // 키보드로 부터 검색할 회원 이름을 받는 Scanner 생성
    			 searchName = scannm.next(); // Scanner => String에 입력 결과 저장
    			 if(membService.searchMember(searchName).size() != 0) { // 검색 결과가 있을 경우
    				 membView.SearchResultSuccess(membService.searchMember(searchName)); // 검색 결과 출력
    				 break;
    			 }else{ // 검색 결과 없을 경우
    				 membView.SearchResultError(membService.searchMember(searchName)); // 검색 결과 없음 출력
    			 }
    		 }
    		 membView.printDeleteNumber(); // 검색한 회원 목록 출력
    		 Scanner deletenum = new Scanner(System.in); // 목록 중 삭제할 번호를 입력 받는 Scanner 선언
    		 num = deletenum.nextInt() - 1; // Scanner => int (-1 하는 이유: 검색된 회원 목록은 ArrayList에 저장됨. ArrayList는 index 구조로 저장되므로 입력받은 값에서 1 빼줘야 한다.) 
    		 if(membService.deleteMember(searchName, num) > 0) { // 1행 이상 삭제되었을 경우
    			 membView.DeleteResultSuccess(); // 삭제 성공 출력
    		 }else { // 삭제된 행이 없을 경우
    			 membView.DeleteResultError(); // 삭제 실패 출력
    		 }
    	 }
//    	 5. 종료
    	 if(selectMenu == 5) { // 종료 메뉴 선택시
    		 membView.printExit(); // 프로그램 종료 문구 출력
    		 scan.close(); // 스캐너 종료
    		 break; // 메뉴 무한 반복 탈출
    	 } 
      }
    } 
   }
