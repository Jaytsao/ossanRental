package _06_article.repository;

import java.util.List;

import _06_article.model.ArticleBean;

public interface ArticleDao {
	
	int getTotalPages();

	List<ArticleBean> getPageArticles(int ossanIds);
	
	List<ArticleBean> getPageArticles();

	void setPageNo(int pageNo);

	// 計算紀錄總筆數
	long getRecordCounts();

	void setArticleId(int aId);

	ArticleBean getArticle();

	int updateArticle(ArticleBean article, long sizeInBytes) ;
	
	int updateArticle(ArticleBean article) ;

	// 依OssanID來查詢單筆記錄
	ArticleBean queryArticle(int aId);

	// 依artNo來刪除單筆記錄
	int deleteArticle(int artNo);

	// 新增一筆記錄
	int saveArticle(ArticleBean article);

	int getTotalPages(int seqNo);
	
	long getRecordCounts(int seqNo);

	long getOssanArticles(int ossanId);

	int saveArticle(ArticleBean art, Integer seqNo);

}