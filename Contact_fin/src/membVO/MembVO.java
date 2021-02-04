package membVO;

public class MembVO { //회원 정보 Data를 저장하는 클래스
   String membnm; // 회원 이름을 저장하는 변수
   String membno; // 회원 전화번호를 저장하는 변수
   String membad; // 회원 주소를 저장하는 변수
   String membtp; // 회원 종류를 저장하는 변수
   
// Getter, Setter  
   public String getMembnm() {
      return membnm;
   }
   public void setMembnm(String membnm) {
      this.membnm = membnm;
   }
   public String getMembno() {
      return membno;
   }
   public void setMembno(String membno) {
      this.membno = membno;
   }
   public String getMembad() {
      return membad;
   }
   public void setMembad(String membad) {
      this.membad = membad;
   }
   public String getMembtp() {
      return membtp;
   }
   public void setMembtp(String membtp) {
      this.membtp = membtp;
   }
// 출력 용이를 위한 toString
@Override
public String toString() {
	return  "[회원이름: "  + membnm 
						+ ", 전화번호: " + membno 
						+ ", 주소: " + membad 
						+ ", 회원종류: " + membtp + "]";
}
   
}