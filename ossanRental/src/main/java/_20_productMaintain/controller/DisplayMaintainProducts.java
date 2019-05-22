package _20_productMaintain.controller;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00_init.util.GlobalService;
import _03_listOssans.model.OssanBean;
import _03_listOssans.service.OssanService;
import _03_listOssans.service.impl.OssanServiceImplHibernate;

@WebServlet("/_20_productMaintain/DisplayMaintainProducts")
public class DisplayMaintainProducts extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int pageNo = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
		serviceHibernate.setPageNo(pageNo);
		serviceHibernate.setRecordsPerPage(GlobalService.RECORDS_PER_PAGE);
		Collection<OssanBean> coll = serviceHibernate.getPageOssans();
		
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalPages", serviceHibernate.getTotalPages());
		request.setAttribute("products_DPP", coll);
		// 交由listOssans.jsp來顯示某頁的書籍資料，同時準備『第一頁』、
		// 『前一頁』、『下一頁』、『最末頁』等資料
		
		//
		session.setAttribute("maintainPageNo", pageNo);
		
		RequestDispatcher rd = request.getRequestDispatcher("OssanMaintainList.jsp");
		rd.forward(request, response);
		return;
	}
}