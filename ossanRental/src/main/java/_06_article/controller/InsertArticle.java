package _06_article.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _00_init.util.GlobalService;
import _00_init.util.SystemUtils2018;
import _03_listOssans.model.OssanBean;
import _06_article.model.ArticleBean;
import _06_article.service.ArticleService;
import _06_article.service.imp.ArticleServiceImpl;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/_06_article/InsertArticle.do")
public class InsertArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertArticle() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		ArticleService service = new ArticleServiceImpl();
		
		OssanBean mob = (OssanBean) session.getAttribute("LoginOK");
//		要編輯文章才會用到文章編號
		Integer ossanNo = mob.getOssanNo(); 
		String title = "";
		String article = "";
		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		// 取出HTTP multipart request內所有的parts
		Collection<Part> arc_Parts = request.getParts();
		GlobalService.exploreParts(arc_Parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (arc_Parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : arc_Parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				// 1. 讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("title")) {
						title = value;
					} else if (fldName.equals("article")) {
						article = value;
					} 
				} else {
					// 取出圖片檔的檔名
					fileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
//					發新文章有上傳圖片跑這行
					if(fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
						ArticleBean articleBean = new ArticleBean();
						try {
							
							Blob blob = SystemUtils2018.fileToBlob(is, sizeInBytes);
							Clob clob = SystemUtils2018.stringToClob(article);							
							articleBean.setsContent(article);
							articleBean.setContent(clob);
							articleBean.setTitle(title);
							articleBean.setArticleImage(blob);
							articleBean.setUpdateTime(ts);
							articleBean.setFileName(fileName);
							service.saveArticle(articleBean,ossanNo);
							session.setAttribute("article", null);
							response.sendRedirect(response.encodeRedirectURL("listArticle"));
							return;
						} catch (SQLException e) {
							e.printStackTrace();
						}
//					發新文章不更新圖片跑這行
					}else{													
							ArticleBean articleBean = new ArticleBean();
							try {					
								fileName = "null.jpg";
								Clob clob = SystemUtils2018.stringToClob(article);
								articleBean.setContent(clob);
								articleBean.setsContent(article);
								articleBean.setTitle(title);
								articleBean.setUpdateTime(ts);
								String path = getServletContext().getRealPath("/images/null.jpg");								
								is = getServletContext().getResourceAsStream(path);
								Blob blob = SystemUtils2018.fileToBlob(is, sizeInBytes);
								articleBean.setFileName(fileName);
								articleBean.setArticleImage(blob);
							} catch (Exception e) {
								e.printStackTrace();
							}
							service.saveArticle(articleBean,ossanNo);
							session.setAttribute("article", null);
							response.sendRedirect(response.encodeRedirectURL("listArticle"));
							return ;
					}

				}
			}
		response.sendRedirect("listArticle");
		return;
		}
	}

}
