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


}
