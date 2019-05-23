package _00_init;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
      
(1) Book欄位說明:
    bookId      	: 書籍代號    			整數     	主鍵, 自動遞增
    title       	: 書名           			字串      
    author      	: 作者               		字串
    price       	: 價格               		DECIMAL(10,1)
    discount    	: 折扣               		DECIMAL(5,3)
    CoverImage  	: 封面照片           		Blob
    fileName    	: 封面圖檔檔名       	字串      	20
    bookNo      	: 書號               		字串      	20
    stock			: 庫存數量			整數
    companyId   	: 出版社代號         		整數      
     
(2) BookCompany欄位說明:
    id 		     	: 出版社代號			整數      	主鍵, 自動遞增
    name         	: 出版社名稱			字串      	60
    address      	: 出版社地址			字串      	60
    url          	: 出版社URL			字串      	120      
      
(3) Member欄位說明:
    seqNo       	: 會員流水號			整數      	主鍵, 自動遞增
    memberId    	: 會員編號           		字串      
    name        	: 姓名               		字串      	
    password    	: 密碼               		字串      	
    address     	: 地址               		字串      	
    email       	: email             字串      	
    tel         	: 電話日             		字串      	
    userType    	: 會員類別           		字串      	
    registerTime	: 註冊時間          		Timestamp
    totalAmount 	: 訂購總金額         		DECIMAL
    memberImage 	: 照片               		Blob
    fileName    	: 封面圖檔檔名      	字串
    comment			: 客戶側寫(註解)		Clob	      	
    unpaid_amount	: 未付款餘額			DECIMAL  
    

    
(4) Orders欄位說明:
    orderNo        	: 訂單編號			整數     	主鍵, 自動遞增
    userId         	: 客戶編號           		字串      	20
    totalAmount    	: 總金額             		DECIMAL(10,1)
    shippingAddress	: 出貨地址           		字串      	64    
    BNO            	: 統一編號           		字串       	8
    invoiceTitle  	: 發票抬頭           		字串      	72
    orderDate      	: 訂單日期			Datetime 
    ShippingDate   	: 出貨日期			Datetime
    CancelTag      	: 取消               		字串       	1
    
(5) OrderItems欄位說明:
    seqNo			: 明細編號			整數      	主鍵, 自動遞增
    orderNo			: 訂單編號			整數		外鍵		
    bookId			: 書籍代號			整數      	外鍵
    Description  	: 說明				字串      	72
    amount       	: 數量				整數
    unitPrice    	: 單價                 		DECIMAL(10,1)
    Discount     	: 折扣                  		DECIMAL(5,3)
 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.DBService;
import _00_init.util.HibernateUtils;
import _00_init.util.SystemUtils2018;
import _03_listOssans.model.OssanBean;
import _06_article.model.ArticleBean;

 
public class EDMTableResetArticleHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
	

	public static void main(String args[]) {
		
//		//JDBC 砍表
//		try (
//				Connection con = DriverManager.getConnection(
//									DBService.getDbUrl(), 
//									DBService.getUser(),
//									DBService.getPassword()); 
//				Statement stmt = con.createStatement();
//			) {			
//				//6.article表格
//				stmt.executeUpdate(DBService.getDropArticle());
//				System.out.println("Article表格刪除成功");
//
//				
//			} catch (SQLException e) {
//				System.err.println("新建表格時發生SQL例外: " + e.getMessage());
//				e.printStackTrace();
//			}
//		
//		
		
		//Hibernate方法開始
				String line = "";
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				int n = 1;
				SessionFactory factory = HibernateUtils.getSessionFactory();
				Session session = factory.getCurrentSession();
				Transaction tx = null;
				try {
					tx = session.beginTransaction();

					File file = new File("data/inputarticle.txt");
					// 2. 由"data/inputarticle.txt"逐筆讀入article表格內的初始資料，然後依序新增
					// 到Book表格中
					try (
						FileInputStream fis = new FileInputStream(file);
						InputStreamReader isr = new InputStreamReader(fis, "UTF8");
						BufferedReader br = new BufferedReader(isr);
					) {
						while ((line = br.readLine()) != null) {
							System.out.println("line=" + line);
							// 去除 UTF8_BOM: \uFEFF
							if (line.startsWith(UTF8_BOM)) {
								line = line.substring(1);
							}
							String[] token = line.split("\\|");
							ArticleBean ossanArticle = new ArticleBean();
							ossanArticle.setUpdateTime(ts);
							ossanArticle.setTitle(token[0]);
							ossanArticle.setContent(SystemUtils2018.fileToClob(token[1]));
							ossanArticle.setArticleImage(SystemUtils2018.fileToBlob(token[3]));							
							OssanBean ob = session.get(OssanBean.class, Integer.parseInt(token[2]));
							
							
							ossanArticle.setOssanBean(ob);
							

							session.save(ossanArticle);
							System.out.println("新增第"+n+"筆Article紀錄成功");
							n++;
						}

						// 印出資料新增成功的訊息
						session.flush();
						System.out.println("Article資料新增成功");
					}
					
		            tx.commit();
				} catch (Exception e) {
					System.err.println("新建表格時發生例外: " + e.getMessage());
					e.printStackTrace();
					tx.rollback();
				} 
		        factory.close();
	
	}
	
}