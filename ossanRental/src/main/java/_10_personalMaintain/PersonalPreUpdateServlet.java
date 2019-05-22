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
import _03_listOssans.service.OssanService;
import _03_listOssans.service.impl.OssanServiceImplHibernate;



@WebServlet("/_10_personalMaintain/PersonalPreUpdate.do")
public class PersonalPreUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int ossanNo =((OssanBean) session.getAttribute("LoginOK")).getOssanNo();		
		OssanService serviceHibernate = new OssanServiceImplHibernate();
		OssanBean ob = serviceHibernate.queryOssan(ossanNo);		
		session.setAttribute("bean", ob);
		RequestDispatcher rd = request.getRequestDispatcher("/_10_personalMaintain/PersonalUpdate.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
