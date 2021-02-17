package Service;

import java.util.ArrayList;

import DAO.DAO;
import VO.AccountVO;
import VO.ContactVO;

public class Service {

	public AccountVO login(String id, String pw) {
		DAO dao = new DAO();
		AccountVO account = dao.login(id, pw);
		return account;
	}

	public void joinAccount(AccountVO account) {
		DAO dao = new DAO();
		dao.joinAccount(account);
	}

	public ArrayList<ContactVO> selectAll(String id) {
		DAO dao = new DAO();
		ArrayList<ContactVO> member = dao.selectAll(id);
		return member;
	}

	public ContactVO selectByPhone(String id, String phone) {
		DAO dao = new DAO();
		ContactVO contact = dao.selectByPhone(id, phone);
		
		return contact;
	}

	public void update(String oldPhone, ContactVO contact) {
		DAO dao = new DAO();
		dao.update(oldPhone, contact);
	}

	
	


}
