package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.Service;
import VO.ContactVO;

@WebServlet("/ModifyServlet")
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public String oldPhone;
    public UpdateContactServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String phone = request.getParameter("phone");
		oldPhone = phone;
		Service service = new Service();
		ContactVO contact = service.selectByPhone(id, phone);
		request.setAttribute("contact", contact);
		RequestDispatcher disp = request.getRequestDispatcher("modifyForm.jsp");
		disp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Service service = new Service();
		ContactVO contact = new ContactVO();
		contact.setName(request.getParameter("name"));
		contact.setPhone(request.getParameter("phone"));
		contact.setAddress(request.getParameter("address"));
		contact.setCategory(request.getParameter("category"));
		service.update(oldPhone, contact);
		response.sendRedirect("MainServlet");
	}

}
