package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.Service;
import VO.AccountVO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("loginForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	AccountVO account = new AccountVO();
	Service service = new Service();
	account = service.login(id, pw);
	if(account.getId() != null) {
//		로그인 성공
		HttpSession session = request.getSession();
		session.setAttribute("name", account.getName());
		session.setAttribute("id", account.getId());
		
		response.sendRedirect("MainServlet");
	}else {
		doGet(request, response);
	}
	}

}
