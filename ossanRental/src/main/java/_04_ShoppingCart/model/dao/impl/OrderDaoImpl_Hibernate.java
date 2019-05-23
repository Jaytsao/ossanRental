package _04_ShoppingCart.model.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import _00_init.util.HibernateUtils;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.OrderItemBean;
import _04_ShoppingCart.model.dao.OrderDao;

// 本類別
//   1.新增一筆訂單到orders表格
//   2.查詢orders表格內的單筆訂單
public class OrderDaoImpl_Hibernate implements OrderDao {

//	private String memberId = null;
	SessionFactory factory;
	int orderNo = 0;

	public OrderDaoImpl_Hibernate() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void insertOrder(OrderBean ob) {
		Session session = factory.getCurrentSession();
		session.save(ob);
	}

	public OrderBean getOrder(int orderNo) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		ob = session.get(OrderBean.class, orderNo);
		return ob;
	}

//	public String getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(String memberId) {
//		this.memberId = memberId;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean";
		Session session = factory.getCurrentSession();

		list = session.createQuery(hql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItemBean> getOssanOrders(int ossanNo) {
		List<OrderItemBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderItemBean oib WHERE oib.seqNo = :mid";
		list = session.createQuery(hql).setParameter("mid", ossanNo).list();
		return list;
	}

}