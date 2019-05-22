package _03_listOssans.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _00_init.util.SystemUtils2018;
import _03_listOssans.model.OssanBean;
import _03_listOssans.repository.OssanDao;

// 本類別使用純JDBC的技術來存取資料庫。
// 所有SQLException都以catch區塊捕捉，然後一律再次丟出RuntimeException。
// 對SQLException而言，即使catch下來，程式依然無法正常執行，所以捕捉SQLException，再次丟出
// RuntimeException。
public class OssanDaoImpl_Hibernate implements Serializable, OssanDao {
	private static final long serialVersionUID = 1L;
	private int ossanNo = 0; 	// 查詢單筆商品會用到此代號
	private int pageNo = 0;		// 存放目前顯示之頁面的編號
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁三筆
	private int totalPages = -1;	
	SessionFactory factory;

	public OssanDaoImpl_Hibernate() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	// 計算販售的商品總共有幾頁
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}	
	@SuppressWarnings("unchecked")
	@Override
	public List<OssanBean> getPageOssans() {
		
		List<OssanBean> list = new ArrayList<OssanBean>();
		String hql = "FROM OssanBean a WHERE a.privilege = :privilege ";
		
		Session session = factory.getCurrentSession();

		int startRecordNo = (pageNo - 1) * recordsPerPage;
		
		list = session.createQuery(hql)
					  .setParameter("privilege","Ossan")
					  .setFirstResult(startRecordNo)
					  .setMaxResults(recordsPerPage)
					  .list();
		return list;
	}
	
	@Override
	public int getPageNo() {
		return pageNo;
	}

	@Override
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	@Override
	public boolean idExists(String id) {
		
		Session session = factory.getCurrentSession();
		boolean exist = false;
		String hql = "FROM OssanBean WHERE email = :email";
		try {
			OssanBean ob = (OssanBean) session.createQuery(hql)
												.setParameter("email", id)
												.uniqueResult();
			if (ob != null) {
				exist = true;
			}
			
		} catch (NoResultException ex) {
			exist = false;
		} catch (NonUniqueResultException ex) {
			exist = true;
		}
		
		return exist;
	}


	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM OssanBean a WHERE a.privilege = :privilege";
		Session session = factory.getCurrentSession();
		count = (long)session.createQuery(hql).setParameter("privilege","Ossan").uniqueResult();
		return count;
	}

	@Override
	public int updateOssan(OssanBean bean, long sizeInBytes) {
		int n = 0;
		
//		if (sizeInBytes == -1) { // 不修改圖片
//			n = updateOssan(bean);
//			return n;
//		}		
//		OssanBean ob = null;
		Session session = factory.getCurrentSession();
//		ob = session.get(OssanBean.class, bean.getOssanNo());
		
		session.update(bean);
		
//		String hql = "UPDATE OssanBean SET " +
//		          "email = :email," +
//		          "password = :password, " +
//		          "name = :name, " +
//		          "uniqueId = :uniqueId, " +
//		          "birthday = :birthday, " +
//		          "phone = :phone, " +
//		          "city = :city, " +
//		          "district = :district, " +
//		          "address = :address, " +
//		          "nickname = :nickname, " +
//		          "ossanImage = :ossanImage, " +
//		          "fileName = :fileName " +
//		          
//		          "where ossanNo = :ossanNo";
		
//		n = session.createQuery(hql)
//				   .setParameter("email", bean.getEmail())
//				   .setParameter("password", bean.getPassword())
//				   .setParameter("name", bean.getName())
//				   .setParameter("uniqueId", bean.getUniqueId())
//				   .setParameter("birthday", bean.getBirthday())
//				   .setParameter("phone", bean.getPhone())
//				   .setParameter("city", bean.getCity())
//				   .setParameter("district", bean.getDistrict())
//				   .setParameter("address", bean.getAddress())
//				   .setParameter("nickname",bean.getNickname())
//				   .setParameter("ossanImage", bean.getOssanImage())
//				   .setParameter("fileName", bean.getFileName())
//				   .setParameter("ossanNo", bean.getOssanNo())
//				   .executeUpdate();
//		
		return n++;
	}

	@Override
	public OssanBean queryOssan(int ossanNo) {
		OssanBean bean = null;
		Session session = factory.getCurrentSession();
		bean = session.get(OssanBean.class, ossanNo);
		
		if (bean.getIntro() != null) {
			
			bean.setsIntro(SystemUtils2018.clobToStringWindows(bean.getIntro()));
		}
		
		return bean;
	}

	@Override
	public int deleteOssan(int ossanNo) {
		int n = 0;
		Session session = factory.getCurrentSession();
		OssanBean bb = new OssanBean();
		bb.setOssanNo(ossanNo);
		session.delete(bb);
		n++;
		return n;
	}

	@Override
	public void setOssanNo(int ossanNo) {
		this.ossanNo = ossanNo;
		
	}

	@Override
	public OssanBean getOssan() {
		OssanBean bean = queryOssan(this.ossanNo);
		return bean;
	}

	@Override
	public int saveOssan(OssanBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		
		java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
		bean.setRegisterTime(now);
		bean.setPrivilege("Ossan");
		
		//地區之後修正
		if(bean.getCity().equals("新北市")) {
			bean.setTwNorth(true);
		} else if (bean.getCity().equals("台中市")) {
			bean.setTwMiddle(true);
		} else if (bean.getCity().equals("台南市")) {
			bean.setTwSouth(true);
		} else {
			bean.setTwOther(true);
		}

		session.save(bean);
		int a = bean.getOssanNo();
		
		System.out.println("產生第"+a+"號大叔 : 個人資料Bean");
		
		
		
		n++;
		return n;
	}

	@Override
	public OssanBean checkIDPassword(String ossanId, String password) {
		OssanBean ob = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OssanBean WHERE email = :mid and password = :pswd";
		try {
			ob = (OssanBean) session.createQuery(hql)
							.setParameter("mid", ossanId)
							.setParameter("pswd", password)
							.uniqueResult();
		} catch (NoResultException ex) {
			ob = null;
		}
		return ob;
	}

	@Override
	public int updateOssanDesc(OssanBean bean) {
		
		int n = 0;
		Session session = factory.getCurrentSession();
		
		String hql = "UPDATE OssanBean SET " +
		          "quote = :quote," +
		          "intro = :intro " +
		          
		          "where ossanNo = :ossanNo";
		
		n = session.createQuery(hql)
				   .setParameter("quote", bean.getQuote())
				   .setParameter("intro",bean.getIntro())
				   .setParameter("ossanNo", bean.getOssanNo())
				   .executeUpdate();
		
		return n;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCountsArea(String area) {
		long count = 0; // 必須使用 long 型態
		
		String hql;
		String hql2 = " and a.twNorth+a.twMiddle+a.twSouth+a.twOther>0";
		
		if (!area.equals("twAll") && area != null && area!="") {
			hql = "SELECT count(*) FROM OssanBean a "
					+ "WHERE a.privilege = :privilege and a."+ area +" = 1";
		} else {
			hql = "SELECT count(*) FROM OssanBean a WHERE a.privilege = :privilege";
		}
		
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql+hql2).setParameter("privilege","Ossan").list();
		System.out.println("執行完Count語法");
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OssanBean> getPageOssansArea(String area) {
		List<OssanBean> list = new ArrayList<OssanBean>();
		String hql;
		String hql2 = " and a.twNorth+a.twMiddle+a.twSouth+a.twOther>0";
		
		if (!area.equals("twAll") && area != null && area!="" ) {
			hql = "FROM OssanBean a "
					+ "WHERE a.privilege = :privilege and a."+ area +" = 1";
		} else {
			hql = "FROM OssanBean a WHERE a.privilege = :privilege";
		}
		
		Session session = factory.getCurrentSession();

		int startRecordNo = (pageNo - 1) * recordsPerPage;

		list = session.createQuery(hql+hql2)
					  .setParameter("privilege","Ossan")
					  .setFirstResult(startRecordNo)
					  .setMaxResults(recordsPerPage)
					  .list();
		
		System.out.println("執行完Query語法");
		return list;
	}

	@Override
	public int getTotalPagesArea(String area) {
		totalPages = (int) (Math.ceil(getRecordCountsArea(area) / (double) recordsPerPage));
		return totalPages;
	}

	@Override
	public int updateOssanArea(OssanBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		
		String hql = "UPDATE OssanBean SET " +
		          "twNorth = :twNorth," +
		          "twMiddle = :twMiddle," +
		          "twSouth = :twSouth," +
		          "twOther = :twOther "+
		          
		          "where ossanNo = :ossanNo";
		
		n = session.createQuery(hql)
				   .setParameter("twNorth", bean.isTwNorth())
				   .setParameter("twMiddle",bean.isTwMiddle())
				   .setParameter("twSouth", bean.isTwSouth())
				   .setParameter("twOther",bean.isTwOther())
				   .setParameter("ossanNo", bean.getOssanNo())
				   .executeUpdate();
		
		return n;
	}
	
}