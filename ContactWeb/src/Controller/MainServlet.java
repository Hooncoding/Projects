package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.Service;
import VO.ContactVO;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
//	MainServlet은 Client가 요청하는 첫 페이지 => 요청은 Get 방식으로 받음
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		
		if(id == null || name == null) {
			response.sendRedirect("welcome.jsp");
		}else {
			ArrayList<ContactVO> member = new ArrayList<ContactVO>();
			Service service = new Service();
			member = service.selectAll(id);
			
			response.sendRedirect("main.jsp");			
		}
		
	
	}

	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
