package _03_listOssans.controller;

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

@WebServlet("/_03_listOssans/DisplayOneProduct.do")
// 本控制器負責進行必要的前置作業，以便Dao取回某一頁的商品資料
public class RetrieveOneProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		// 讀取瀏覽送來的 pageNo
		HttpSession session = request.getSession();
		OssanBean ossanBean = (OssanBean)session.getAttribute("LoginOK");
		String pKey = request.getParameter("pKey");
		if(pKey == null) {
		pKey = String.valueOf(ossanBean.getOssanNo());
		}
		int ossanNo = Integer.parseInt(pKey);		
		OssanService serviceHibernate = new OssanServiceImplHibernate();
		OssanBean ob = serviceHibernate.queryOssan(ossanNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pKey", pKey);		
		request.setAttribute("aOssanBean", ob);
		RequestDispatcher rd = request.getRequestDispatcher("listOneOssan.jsp");
		rd.forward(request, response);
		return;

	}
}