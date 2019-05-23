package _05_orderProcess.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_listOssans.model.OssanBean;
import _04_ShoppingCart.model.OrderItemBean;
import _04_ShoppingCart.model.service.OrderService;
import _04_ShoppingCart.model.service.impl.OrderServiceImplHibernate;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/_05_orderProcess/oneOssanOrderList.do")
public class OneOssanOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
//		if (session == null) {      // 使用逾時
//			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
//			return;
//		}
		OssanBean mb = (OssanBean) session.getAttribute("LoginOK");
		Integer key = mb.getOssanNo();
//		Integer key = (Integer)session.getAttribute("OssanSessionPKey");
		
		OrderService os = new OrderServiceImplHibernate();
		
		List<OrderItemBean> oneOssanOrders = os.getOssanOrders(key);
		
		request.setAttribute("allOrders", oneOssanOrders);
		RequestDispatcher rd = request.getRequestDispatcher("/_05_orderProcess/OneOssanOrderList.jsp");
		rd.forward(request, response);
		return;
		
	}

}
