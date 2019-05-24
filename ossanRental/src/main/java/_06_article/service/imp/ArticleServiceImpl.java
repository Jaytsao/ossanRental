package _06_article.service.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;
import _06_article.model.ArticleBean;
import _06_article.repository.ArticleDao;
import _06_article.repository.imp.ArticleDaoImpl_Hibernate;
import _06_article.service.ArticleService;

public class ArticleServiceImpl implements ArticleService{
	
	ArticleDao dao ;
	SessionFactory factory = null;
	
	public ArticleServiceImpl() {
		this.dao = new ArticleDaoImpl_Hibernate();
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public int getTotalPages() {
		Session session = factory.getCurrentSession();
		int n =0 ;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.getTotalPages();
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n;
	}
	
	@Override
	public int getTotalPages(int seqNo) {
		Session session = factory.getCurrentSession();
		int n =0 ;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.getTotalPages(seqNo);
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n;
	}

	@Override
	public List<ArticleBean> getPageArticles( int ossanId) {
		List<ArticleBean> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list =  dao.getPageArticles(ossanId);
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
	//for visitor and admin
	@Override
	public List<ArticleBean> getPageArticles() {
		List<ArticleBean> list = null ;
		Session session = factory.getCurrentSession();
		Transaction tx = null ;
		try {
			tx = session.beginTransaction();
			list = dao.getPageArticles();
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
	

	@Override
	public void setPageNo(int pageNo) {
		dao.setPageNo(pageNo);
	}

	

	@Override
	public int updateArticle(ArticleBean article, long sizeInBytes) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = dao.updateArticle(article, sizeInBytes);
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n ;
	}

	@Override
	public int updateArticle(ArticleBean article) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		int n = 0;
		try {
			tx = session.beginTransaction();
			n = dao.updateArticle(article);
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n;
	}

	@Override
	public int deleteArticle(int artNo) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		int n = 0 ;
		try {
			tx = session.beginTransaction();
			n = dao.deleteArticle(artNo);
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n;
	}

	@Override
	public int saveArticle(ArticleBean art, Integer seqNo) {
		Session session = factory.getCurrentSession();
		int n = 0;
		Transaction tx = null ;
		try {
			tx = session.beginTransaction();
			n = dao.saveArticle(art,seqNo);
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n ;
	}
	

	public ArticleBean getArticle(int aId) {
//		dao.setArticleId(aId);
		Session session = factory.getCurrentSession();
		ArticleBean article = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			article = dao.getArticle();
			article = dao.queryArticle(aId);
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return article;
	}

	@Override
	public long getOssanArticles(int ossanId) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		long n = 0 ;
		try {
			tx =session.beginTransaction();
			n = dao.getOssanArticles(ossanId);
			tx.commit();
		}catch (Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return n ;
	}



}
