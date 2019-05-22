package _06_article.repository.imp;


import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _00_init.util.SystemUtils2018;
import _03_listOssans.model.OssanBean;
import _03_listOssans.repository.OssanDao;
import _03_listOssans.repository.impl.OssanDaoImpl_Hibernate;
import _06_article.model.Article;
import _06_article.repository.ArticleDao;

public class ArticleDaoImpl_Hibernate implements ArticleDao{
	
	
	private int aId = 0; 	// 查詢單筆文章會用到此代號
	private int pageNo = 1;		// 存放目前顯示之頁面的編號
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁三筆
	private int totalPages = -1;

	SessionFactory factory = null;
	
	//負責取SessionFactory
	public ArticleDaoImpl_Hibernate() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
				totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));

				return totalPages;
	}
	//取出大叔自己的文章(ossan用，一頁)
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getPageArticles(int ossanId) {
		List<Article> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Article a WHERE a.ossanbean.ossanNo = :ossanId ORDER BY a.updateTime DESC";
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		list = session.createQuery(hql).setParameter("ossanId", ossanId).
				setFirstResult(startRecordNo).
				setMaxResults(recordsPerPage).
				list();
		
			for(Article art : list) {
				art.setsArticle(SystemUtils2018.clobToStringWindows(art.getArticle()));
			}		
		
		return list;
	}
	//取出所有的文章(visitor , admin用，一頁)
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getPageArticles() {
		List<Article> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Article a ORDER BY a.updateTime DESC";
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		list = session.createQuery(hql).
				setFirstResult(startRecordNo).
				setMaxResults(recordsPerPage).
				list();
		for(Article art : list) {
			art.setsArticle(SystemUtils2018.clobToStringWindows(art.getArticle()));
		}
		return list;
	}

	@Override
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	//所有文章筆數
	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM Article";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql).list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	@Override
	public void setArticleId(int aId) {
		this.aId = aId;
		
	}

	@Override
	public Article getArticle() {
		Article article = queryArticle(this.aId);
		return article;
	}

	@Override
	public int updateArticle(Article article, long sizeInBytes) {
		int n = 0;
		Session session = factory.getCurrentSession();
		if(article.getOssanbean() == null) {
			OssanDao dao = new OssanDaoImpl_Hibernate();
			OssanBean mob = dao.queryOssan(article.getOssanbean().getOssanNo());
			article.setOssanbean(mob);
		}
		session.merge(article);
		n++;
		return n;
	}
	@Override
	public int updateArticle(Article article) {
		int n = 0;
		Session session = factory.getCurrentSession();
		
//		if(article.getOssanbean() == null) {
//			OssanDao dao = new OssanDaoImpl_Hibernate();
//			OssanBean mob = session.get(OssanBean.class, Integer.parseInt(article.getSeqNo()));
//			String r = article.getSeqNo();
//			OssanBean mob = dao.queryOssan(Integer.parseInt(r));
//			article.setOssanbean(mob);
//		}

		session.update(article);
		n++;
		return n;
	}

	@Override
	public Article queryArticle(int aId) {
		Article article = null ;
		Session session = factory.getCurrentSession();
		article = session.get(Article.class, aId);
		return article;
	}
	@Override
	public int deleteArticle(int artNo) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Article art = new Article();
		art.setArtNo(artNo);
		session.delete(art);
		n++;
		return n;
	}

	@Override
	public int saveArticle(Article article) {
		int n = 0;
		Session session = factory.getCurrentSession();
		OssanBean mob = session.get(OssanBean.class, Integer.parseInt(article.getSeqNo()));
		article.setOssanbean(mob);
		session.save(article);
		n++;
		return n;
	}

	@Override
	public int getTotalPages(int seqNo) {
		totalPages = (int) (Math.ceil(getRecordCounts(seqNo) / (double) recordsPerPage));
		if(totalPages < 1) { //預防大叔還沒寫文章
			return 1 ;
		}else {
			return totalPages;
		}
	}
	//大叔的發文筆數
	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCounts(int seqNo) {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM Article a WHERE a.ossanbean.ossanNo = :no";
		Session session = factory.getCurrentSession();
		List<Long> list = session.createQuery(hql).setParameter("no", seqNo).list();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	@Override
	public long getOssanArticles(int ossanId) {
		long n = 0;
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(*) FROM Article a WHERE a.ossanbean.ossanNo = :id ";
		 n = (long)session.createQuery(hql).setParameter("id", ossanId).uniqueResult();
		return n;
	}

}
