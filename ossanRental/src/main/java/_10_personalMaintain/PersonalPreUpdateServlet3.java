package _10_personalMaintain;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_listOssans.model.OssanBean;


@WebServlet("/_10_personalMaintain/PersonalPreUpdate3.do")
public class PersonalPreUpdateServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		
		OssanBean ob = (OssanBean) session.getAttribute("bean");

		
		Boolean twNorth = ob.isTwNorth();
		Boolean twMiddle = ob.isTwMiddle();
		Boolean twSouth = ob.isTwSouth();
		Boolean twOther = ob.isTwOther();

		request.setAttribute("twNorth", twNorth);
		request.setAttribute("twMiddle", twMiddle);
		request.setAttribute("twSouth", twSouth);
		request.setAttribute("twOther", twOther);


		RequestDispatcher rd = request.getRequestDispatcher("/_10_personalMaintain/PersonalUpdate3.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
