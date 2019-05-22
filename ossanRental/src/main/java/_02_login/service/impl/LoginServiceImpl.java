package _02_login.service.impl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import _00_init.util.HibernateUtils;
import _02_login.service.LoginService;
import _03_listOssans.model.OssanBean;
import _03_listOssans.repository.OssanDao;
import _03_listOssans.repository.impl.OssanDaoImpl_Hibernate;


// 登入時系統必須執行的checkIDPassword()功能交由 MemberDao來完成 
public class LoginServiceImpl implements LoginService {
	
	OssanDao  dao ;
	SessionFactory factory;
	public LoginServiceImpl() {
		dao = new OssanDaoImpl_Hibernate();
		factory = HibernateUtils.getSessionFactory();
	}	
	public OssanBean checkIDPassword(String userId, String password) {
		OssanBean ob = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		    ob = dao.checkIDPassword(userId, password);
		    tx.commit();
		} catch(Exception ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} 
		return ob;
	}
}
