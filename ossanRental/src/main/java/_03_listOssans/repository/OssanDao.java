package _03_listOssans.repository;

import java.util.List;

import _03_listOssans.model.OssanBean;

public interface OssanDao {
	
	boolean idExists(String ossanId);
	
	int getTotalPages();
	
	int getTotalPagesArea(String area);

	List<OssanBean> getPageOssans();
	
	List<OssanBean> getPageOssansArea(String area);

	int getPageNo();

	void setPageNo(int pageNo);

	int getRecordsPerPage();

	void setRecordsPerPage(int recordsPerPage);

	// 計算紀錄總筆數
	long getRecordCounts();
	
	long getRecordCountsArea(String area);

	void setOssanNo(int ossanNo);

	OssanBean getOssan();

	int updateOssan(OssanBean bean, long sizeInBytes) ;

	// 修改一筆記錄
	
	// 修改一筆放 quote & intro 的 Bean
	int updateOssanDesc(OssanBean bean);
//	
	int updateOssanArea(OssanBean bean);

	// 依ossanNo來查詢單筆記錄
	OssanBean queryOssan(int ossanNo);

	// 依ossanNo來刪除單筆記錄
	int deleteOssan(int ossanNo);

	// 新增一筆記錄
	int saveOssan(OssanBean bean);
	
	public OssanBean checkIDPassword(String ossanId, String password);	
	

}