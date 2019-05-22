package _03_listOssans.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;
import _03_listOssans.model.OssanBean;
import _03_listOssans.repository.OssanDao;
import _03_listOssans.repository.impl.OssanDaoImpl_Hibernate;
import _03_listOssans.service.OssanService;

public class OssanServiceImplHibernate implements OssanService {
	
    OssanDao dao;
    SessionFactory factory;
    
	public OssanServiceImplHibernate() {
		this.dao = new OssanDaoImpl_Hibernate();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public int getTotalPages() {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.getTotalPages();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public List<OssanBean> getPageOssans() {
		List<OssanBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = dao.getPageOssans();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}

	@Override
	public int getPageNo() {
		return dao.getPageNo();
	}

	@Override
	public void setPageNo(int pageNo) {
		dao.setPageNo(pageNo);
	}

	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);
	}

	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}

	@Override
	public OssanBean getOssan(int ossanNo) {
		OssanBean bean = null;
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		    dao.setOssanNo(ossanNo);
		    bean = dao.getOssan();
		    tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return bean;
	}

	@Override
	public int updateOssan(OssanBean bean, long sizeInBytes) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.updateOssan(bean, sizeInBytes);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return n;
	}
	@Override
	public OssanBean queryOssan(int OssanId) {
		OssanBean bean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bean = dao.queryOssan(OssanId);
		    tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
		return bean;
	}

	@Override
	public int deleteOssan(int no) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.deleteOssan(no);
		    tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public int saveOssan(OssanBean bean) {
		int n = 0;
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.saveOssan(bean);
		    tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
		return n;
	}

	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		    exist = dao.idExists(id);
			tx.commit();
		} catch(Exception ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} 
		return exist ;
	}

	@Override
	public int updateOssanDesc(OssanBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.updateOssanDesc(bean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public long getRecordCountsArea(String area) {
		return dao.getRecordCountsArea(area);
		
	}

	@Override
	public List<OssanBean> getPageOssansArea(String area) {
		List<OssanBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = dao.getPageOssansArea(area);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return list;
	}

	@Override
	public int getTotalPagesArea(String area) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.getTotalPagesArea(area);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public int updateOssanArea(OssanBean bean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.updateOssanArea(bean);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return n;
	}

}
