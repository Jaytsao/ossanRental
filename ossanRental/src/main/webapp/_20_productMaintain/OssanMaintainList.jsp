<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>顯示商品資訊</title>
	
	<script type="text/javascript">
	
		function confirmDelete() {
			if (confirm("確定刪除此項大叔資料(編號:${aOssanBean.ossanNo}  姓名:${aOssanBean.name})?") ) {
				document.forms[0].action="/_20_productMaintain/OssanDelete.do?pageNo=${param.pageNo}&pKey=${aOssanBean.ossanNo}&name=${aOssanBean.name}" ;
				document.forms[0].method="POST";
				document.forms[0].submit();
			} else {
			}
		}
	</script>
	
</head>

<body>

<jsp:include page="/fragment/top.jsp" />
<div style="padding-left:50px">
${ OssanDeleteMsg }
<c:remove var="OssanDeleteMsg" />
</div>
<hr>
<table class="pure-table">
    <thead>
        <tr>
            <th>#</th>
            <th>圖檔</th>
            <th>姓名</th>
            <th>暱稱</th>
            <th>格言</th>
            <th></th>
        </tr>
    </thead>

    <tbody>
    	<c:forEach varStatus="stVar"  var="aOssanBean"  items="${products_DPP}" >
        <tr>
            <td>${aOssanBean.ossanNo}</td>
            <td>
            <img width='100' 
     src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${aOssanBean.ossanNo}&type=OSSAN'>
            </td>
            <td>${aOssanBean.name}</td>
            <td>${aOssanBean.nickname}</td>
            
            <td style="white-space: pre">${aOssanBean.quote}</td>
            <td>
            	<form action="OssanDelete.do?pageNo=${param.pageNo}&pKey=${aOssanBean.ossanNo}&name=${aOssanBean.name}" method="post">
             		<button type="submit" class="button-small pure-button " 
             		onClick="return confirm('確定要刪除第${aOssanBean.ossanNo}號大叔${aOssanBean.name}?此動作將無法復原');">刪除帳號</button>
             	</form>
            </td>
            
        </tr>
        
        
        </c:forEach> 
        <tr>
        	<td>
		        <c:if test="${pageNo > 1}">
		           <div id="pfirst">
		              <a href="<c:url value='DisplayMaintainProducts?pageNo=1' />">第一頁</a>
		           </div>
		        </c:if>
        	</td>
        	<td>
		        <c:if test="${pageNo > 1}">
		           <div id="pprev">
		              <a href="<c:url value='DisplayMaintainProducts?pageNo=${pageNo-1}' />">上一頁</a>
		           </div>
		        </c:if>       
        	</td>
        	<td>
	            <c:if test="${pageNo != totalPages}">
	                <div id="pnext">
	                   <a href="<c:url value='DisplayMaintainProducts?pageNo=${pageNo+1}' />">下一頁</a>
	                </div>
	            </c:if>       	
        	</td>
        	<td>
	            <c:if test="${pageNo != totalPages}">
	                <div id="plast">
	                    <a href="<c:url value='DisplayMaintainProducts?pageNo=${totalPages}' />">最末頁</a>
	                </div>
	            </c:if>        	
        	</td>
        	<td>
				 第${pageNo}頁 / 共${totalPages}頁
        	</td>
        	<td></td>
        	<td></td>
        </tr>
    </tbody>
</table>
</body>
</html>