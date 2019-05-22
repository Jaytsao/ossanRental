package _03_listOssans.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_listOssans.model.OrderItem;
import _04_ShoppingCart.model.ShoppingCart;

// 當使用者按下『加入購物車』時，瀏覽器會送出請求到本程式
@WebServlet("/_03_listOssans/BuyOssan.do")
public class BuyOssanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
		HttpSession session = request.getSession(false); 
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");
		// 如果找不到ShoppingCart物件
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			session.setAttribute("ShoppingCart", cart);   
		}
		String pKeyStr 		= request.getParameter("pKey");
		String email 	= request.getParameter("email");
		String name 		= request.getParameter("name");
		String nickname 	= request.getParameter("nickname");
		String qtyStr 		= request.getParameter("qty");
		Double price;
		Double discount;
		int qty = 0 ; 
		int pKey = 0;
		try{
			// 進行資料型態的轉換
			qty = Integer.parseInt(qtyStr.trim());
			pKey = Integer.parseInt(pKeyStr.trim());
//			price = Double.parseDouble(priceStr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}		
		//暫時放一筆固定價格, Jay 20190503
		price = (double) 300;
		discount = (double) 1;		
		// 將訂單資料封裝到OrderItem物件內
		OrderItem oi = new OrderItem(pKey,email,name,nickname,qty,price,discount);
		// 將OrderItem物件內加入ShoppingCart的物件內
		cart.addToCart(pKey, oi);		
		String pageNo = (String) session.getAttribute("pageNo");
		String sArea = (String) session.getAttribute("sessionArea");		
		response.sendRedirect(response.encodeRedirectURL ("../_03_listOssans/DisplayOssanProducts?pageNo="+pageNo+"&requestArea="+sArea));
		return;
	}
}