package _03_listOssans.controller;

import java.io.IOException;
import java.util.Collection;

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

@WebServlet("/_03_listOssans/DisplayOssanProducts")
// 本控制器負責進行必要的前置作業，以便Dao取回某一頁的商品資料
public class RetrieveOssanProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先取出session物件
		HttpSession session = request.getSession(false);		
		String pageNoStr = request.getParameter("pageNo");		
		if (pageNoStr == null) {  
			pageNo = 1;
		} else {
			try {
				pageNo = Integer.parseInt(pageNoStr.trim());
			} catch (NumberFormatException e) {
				pageNo = 1;
			}
		}		
		OssanService serviceHibernate = new OssanServiceImplHibernate();		
		//地區判別
		String rArea = request.getParameter("requestArea");
		String sArea = (String) session.getAttribute("sessionArea");
			if(!rArea.equals(sArea)) {
				pageNo = 1;
			}			
			serviceHibernate.setPageNo(pageNo);
			Collection<OssanBean> coll = serviceHibernate.getPageOssansArea(rArea);
			session.setAttribute("pageNo", String.valueOf(pageNo));
			request.setAttribute("totalPages", serviceHibernate.getTotalPagesArea(rArea));
			// 將讀到的一頁資料放入request物件內，成為它的屬性物件
			request.setAttribute("products_DPP", coll);
			session.setAttribute("sessionArea",rArea);		
		RequestDispatcher rd = request.getRequestDispatcher("listOssans.jsp");
		rd.forward(request, response);
		return;
		
	}
}