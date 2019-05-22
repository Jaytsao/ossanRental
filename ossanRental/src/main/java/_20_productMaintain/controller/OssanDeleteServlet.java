package _20_productMaintain.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import _03_listOssans.service.OssanService;
import _03_listOssans.service.impl.OssanServiceImplHibernate;

@WebServlet("/_20_productMaintain/OssanDelete.do")
public class OssanDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String pKey = request.getParameter("pKey");
		String name = request.getParameter("name");
		int ossanNo = Integer.parseInt(pKey);
//		OssanService service = new OssanServiceImpl();
		OssanService serviceHibernate = new OssanServiceImplHibernate();
		int n = serviceHibernate.deleteOssan(ossanNo);
		if (n == 1) {
			session.setAttribute("OssanDeleteMsg", "大叔(" + name + ")刪除成功");
		} else {
			session.setAttribute("OssanDeleteMsg", "大叔(" + name + ")刪除失敗");
		}
		response.sendRedirect("DisplayMaintainProducts");
		return;

	}

}
