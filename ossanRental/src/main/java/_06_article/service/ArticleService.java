package _06_article.service;

import java.util.List;

import _06_article.model.Article;

public interface ArticleService {
	
	int getTotalPages();
	
	List<Article> getPageArticles();
	
	List<Article> getPageArticles(int ossanId);
	
	Article getArticle(int aId) ;

	void setPageNo(int pageNo);

	int updateArticle(Article article, long sizeInBytes) ;

	// 修改一筆記錄
	int updateArticle(Article article) ;


	// 依artNo來刪除單筆記錄
	int deleteArticle(int artNo);

	// 新增一筆記錄
	int saveArticle(Article article);
	
	int getTotalPages(int seqNo);

	long getOssanArticles(int ossanId);
	
}