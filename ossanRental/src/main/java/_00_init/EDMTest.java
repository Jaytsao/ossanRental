package _00_init;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
 
*/


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import _00_init.util.HibernateUtils;
import _00_init.util.SystemUtils2018;
import _03_listOssans.model.OssanAreaBean;
import _03_listOssans.model.OssanBean;
import _03_listOssans.model.OssanDescBean;
import _06_article.model.Article;
import _06_article.repository.imp.MemberOssanBean;

public class EDMTest {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
					
			int n = 0;
			
			OssanBean bean = null;
			
			bean = session.get(OssanBean.class, 2);
			
			String hql = "UPDATE OssanBean SET " +
			          "email = :email," +
			          "password = :password, " +
			          "name = :name, " +
			          "uniqueId = :uniqueId, " +
			          "birthday = :birthday, " +
			          "phone = :phone, " +
			          "city = :city, " +
			          "district = :district, " +
			          "address = :address, " +
			          "nickname = :nickname " +
			          
			          "where ossanNo = :ossanNo";
			
			n = session.createQuery(hql)
					   .setParameter("email", bean.getEmail())
					   .setParameter("password", bean.getPassword())
					   .setParameter("name", bean.getName())
					   .setParameter("uniqueId", bean.getUniqueId())
					   .setParameter("birthday", bean.getBirthday())
					   .setParameter("phone", bean.getPhone())
					   .setParameter("city", bean.getCity())
					   .setParameter("district", bean.getDistrict())
					   .setParameter("address", bean.getAddress())
					   .setParameter("nickname",bean.getNickname())
					   .setParameter("ossanNo", bean.getOssanNo())
					   .executeUpdate();
			
			System.out.println(n);
			
			
			
			
			session.flush();
            tx.commit();
		} catch (Exception e) {
			System.err.println("新建表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		} 
        factory.close();
	}

}