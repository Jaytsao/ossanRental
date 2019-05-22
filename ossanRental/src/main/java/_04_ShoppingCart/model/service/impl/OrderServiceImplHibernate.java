package _04_ShoppingCart.model.service.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.OrderItemBean;
import _04_ShoppingCart.model.dao.OrderDao;
import _04_ShoppingCart.model.dao.impl.OrderDaoImpl_Hibernate;
import _04_ShoppingCart.model.service.OrderService;

public class OrderServiceImplHibernate implements OrderService {

	private SessionFactory factory;
	private OrderDao odao;

	public OrderServiceImplHibernate() {
		factory = HibernateUtils.getSessionFactory();
		odao  = new OrderDaoImpl_Hibernate();
	}

	@Override 
	// 這是一個交易
	public void persistOrder(OrderBean ob) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			odao.insertOrder(ob);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());
			throw new RuntimeException(e);
		} 

	}

//	@Override
	// 本方法為過渡版本
	public OrderBean getOrder(int orderNo) {
		OrderBean  bean = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		    bean = odao.getOrder(orderNo);
//		    tx.commit();
//		} catch (Exception e) {
//			if (tx != null) tx.rollback();
//			throw new RuntimeException(e);
//		} 
		return bean;
	}

//	@Override
//	// 本方法將由控制 Lazy Loading 的過濾器之doFilter()方法間接呼叫，所以不可以在此方法內執行與交易
//	// 有關的方法
//	public OrderBean getOrder(int orderNo) {
//		OrderBean  bean = null;
//		//Session session = factory.getCurrentSession();
//		//Transaction tx = null;
//		//try {
//			//tx = session.beginTransaction();
//		    bean = odao.getOrder(orderNo);
//		    //tx.commit();
//		//} catch (Exception e) {
//			//if (tx != null) tx.rollback();
//			//throw new RuntimeException(e);
//		//} 
//		return bean;
//	}

	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = odao.getAllOrders();
		    tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw new RuntimeException(e);
		} 
		return list;
	}

	@Override
	public List<OrderItemBean> getOssanOrders(int ossanNo) {
		List<OrderItemBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = odao.getOssanOrders(ossanNo);
		    tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			throw new RuntimeException(e);
		} 
		return list;
	}

}
