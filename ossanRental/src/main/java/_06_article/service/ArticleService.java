package _06_article.service;

import java.util.List;

import _06_article.model.ArticleBean;

public interface ArticleService {
	
	int getTotalPages();
	
	List<ArticleBean> getPageArticles();
	
	List<ArticleBean> getPageArticles(int ossanId);
	
	ArticleBean getArticle(int aId) ;

	void setPageNo(int pageNo);

	int updateArticle(ArticleBean article, long sizeInBytes) ;

	// 修改一筆記錄
	int updateArticle(ArticleBean article) ;


	// 依artNo來刪除單筆記錄
	int deleteArticle(int artNo);

	// 新增一筆記錄
	int saveArticle(ArticleBean article);
	
	int getTotalPages(int seqNo);

	long getOssanArticles(int ossanId);

	int saveArticle(ArticleBean art, Integer seqNo);
	
}