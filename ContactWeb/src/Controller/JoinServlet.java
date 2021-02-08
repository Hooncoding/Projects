package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Service;
import VO.AccountVO;

@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("joinForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AccountVO account = new AccountVO();
		account.setName(request.getParameter("name"));
		account.setId(request.getParameter("id"));
		account.setPw(request.getParameter("pw"));
		account.setPhonenum(request.getParameter("phonenum"));
		account.setGender(request.getParameter("gender"));
		Service service = new Service();
		service.joinAccount(account);
		response.sendRedirect("MainServlet");
	}

}
