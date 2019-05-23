package _06_article.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_listOssans.model.OssanBean;
import _06_article.model.ArticleBean;
import _06_article.service.ArticleService;
import _06_article.service.imp.ArticleServiceImpl;

@WebServlet("/_06_article/listArticle")
// 本控制器負責取出大叔自己發的文章給大叔
public class ListArticle_Osson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo_up = 1;  //up是update

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先取出session物件
		HttpSession session = request.getSession(false);


		// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
		OssanBean mob = (OssanBean) session.getAttribute("LoginOK");
		ArticleService service = new ArticleServiceImpl(); 
		// 取出使用者的memberId，後面的Cookie會用到 
		String memberId = String.valueOf(mob.getOssanNo());
		int seqNo = mob.getOssanNo();
		// 讀取瀏覽送來的 pageNo
		String pageNoStr = request.getParameter("pageNo_up");
		// 如果讀不到，代表第一次點選大叔故事
		if (pageNoStr == null) {  
			pageNo_up = 1;
			// 讀取瀏覽器送來的所有 Cookies
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				// 逐筆檢視Cookie內的資料
				for (Cookie c : cookies) {
					if (c.getName().equals(memberId + "pageNo_up")) {
						try {
							pageNo_up = Integer.parseInt(c.getValue().trim());
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		} else {
			try {
				pageNo_up = Integer.parseInt(pageNoStr.trim());
			} catch (NumberFormatException e) {
				pageNo_up = 1;
			}
		}
		
		// 讀取一頁的文章資料之前，告訴service，現在要讀哪一頁
		service.setPageNo(pageNo_up);
		// service.getPageArticles()方法開始讀取一頁的文章
		Collection<ArticleBean> coll = service.getPageArticles(seqNo);
		
		session.setAttribute("pageNo_up", pageNo_up);
		request.setAttribute("totalPages_up", service.getTotalPages(seqNo)); //個人頁面的文章數
		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		request.setAttribute("products_DPP", coll);
		

//		 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
//		 -----------------------
		Cookie pn_upCookie = new Cookie(memberId + "pageNo_up", String.valueOf(pageNo_up));
	    // 設定Cookie的存活期為30天
		pn_upCookie.setMaxAge(30 * 24 * 60 * 60);
	    // 設定Cookie的路徑為 Context Path		
		pn_upCookie.setPath(request.getContextPath());
		// 將Cookie加入回應物件內
		response.addCookie(pn_upCookie);
//		 -----------------------
//		 交由PersonalArticle.jsp來顯示某頁的書籍資料，同時準備『第一頁』、
//		 『前一頁』、『下一頁』、『最末頁』等資料

		RequestDispatcher rd = request.getRequestDispatcher("PersonalArticle.jsp");
		rd.forward(request, response);
		return;

	}
}