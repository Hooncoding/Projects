package Service;

import DAO.DAO;
import VO.AccountVO;

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

}
