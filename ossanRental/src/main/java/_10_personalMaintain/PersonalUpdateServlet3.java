package _10_personalMaintain;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _03_listOssans.model.OssanBean;
import _03_listOssans.service.OssanService;
import _03_listOssans.service.impl.OssanServiceImplHibernate;


@WebServlet("/_10_personalMaintain/PersonalUpdate3.do")
@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class PersonalUpdateServlet3 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	OssanBean bb ;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Boolean twNorth = false;
		Boolean twMiddle = false;
		Boolean twSouth = false;
		Boolean twOther = false;
		
		if (!session.isNew())  {
			bb = (OssanBean) session.getAttribute("bean");
		} else {
			bb = new OssanBean();
		}
		
		try {		

			Collection<Part> parts = request.getParts();

			if (parts != null) { // 如果這是一個上傳資料的表單
				for (Part p : parts) {
					String fldName = p.getName();

					String value = request.getParameter(fldName);
					if (p.getContentType() == null) {   // 表示 p 為一般欄位而非上傳的表單
						// 根據欄位名稱來讀取欄位的內容，然後存入適當的變數內
						//PartII	
						if (fldName.equals("twNorth")) {
							twNorth = Boolean.valueOf(value);
							
						} else if (fldName.equals("twMiddle")) {
							twMiddle = Boolean.valueOf(value);
	
						} else if (fldName.equals("twSouth")) {
							twSouth = Boolean.valueOf(value);
							
						} else if (fldName.equals("twOther")) {
							twOther = Boolean.valueOf(value);

						}			
					} 
				}	
			} 
			
			request.setAttribute("twNorth", twNorth);
			request.setAttribute("twMiddle", twMiddle);
			request.setAttribute("twSouth", twSouth);
			request.setAttribute("twOther", twOther);
			
			//這區要處理資料庫
			OssanService ossanServiceHibernate = new OssanServiceImplHibernate();
			OssanBean odb = new OssanBean(bb.getOssanNo(),twNorth,twMiddle,twSouth,twOther);
			ossanServiceHibernate.updateOssanArea(odb);
			
			//這區是更新再Session裡的Bean
			bb.setTwNorth(twNorth);
			bb.setTwMiddle(twMiddle);
			bb.setTwSouth(twSouth);
			bb.setTwOther(twOther);

			session.setAttribute("bean",bb);
			request.setAttribute("updateSuccess", "地區顯示變更成功");
			
			RequestDispatcher rd = request.getRequestDispatcher("PersonalUpdate3.jsp");
			rd.forward(request, response);
			return;
	
		} catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("OssanError.jsp");
			rd.forward(request, response);
		}
	}
}
