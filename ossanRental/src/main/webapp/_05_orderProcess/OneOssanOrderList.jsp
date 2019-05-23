<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/minimalistBlack.css" type="text/css" />
</head>
<body>
<c:set var="funcName" value="ORDossan" scope="session"/>

<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/top.jsp" />


	<table class="minimalistBlack">
	    <thead>
	        <tr>
	        	<th>項次</th>
	        	
	            <th>訂單編號</th>           
	            <th>訂購日期</th>
	            <th>購買時數</th>
	            <th>下單金額</th>
	            
	            <th>訂購姓名</th>
	            <th>聯絡電話</th>
	            <th>電子郵件</th>
	            <th>發票地址</th>

	            <th>客戶留言</th>
	            
	            
<!-- 	            <th>取消標註</th> -->
	            
	        </tr>
	    </thead>
	
	    <tbody>
	    	<c:forEach varStatus="stVar"  var="aSingleOrder"  items="${allOrders}" >
		        <tr style="height:20px">
		            <td>${aSingleOrder.seqNo}</td>
		            
		            <td>${aSingleOrder.orderBean.orderNo}</td>
		            <td>${aSingleOrder.orderBean.orderDate}</td>
		            <td>${aSingleOrder.quantity}</td>
		            <td>${aSingleOrder.orderBean.totalAmount}</td>
		            
		            <td>${aSingleOrder.orderBean.invoiceTitle}</td>
		            <td>${aSingleOrder.orderBean.phone}</td>
		            <td>${aSingleOrder.orderBean.email}</td>
		            <td>${aSingleOrder.orderBean.city}${aSingleOrder.orderBean.district}${aSingleOrder.orderBean.address}</td>
		            <td>${aSingleOrder.orderBean.comment}</td>    
		
		        </tr>
			</c:forEach>
	    </tbody>
	</table>

	

<!--     <p/> -->
<!-- 	<table style="margin-left:auto; margin-right:auto; width:810; background:#F5EBFF; border:2px solid blue; border-style: outset; "> -->

<!-- 		<tr id='borderA' height='50' > -->
<%-- 			<th id='borderA'  colspan="4" align="center">${LoginOK.name}的訂購紀錄</th> --%>
<!-- 		</tr> -->
<!-- 		<tr id='borderA' height='36' > -->
<!-- 			<th id='borderA'>訂單編號</th> -->
<!-- 			<th id='borderA'>訂購日期</th> -->
<!-- 			<th id='borderA'>總金額</th> -->
<!-- 			<th id='borderA'>送貨地址</th> -->
<!-- 		</tr> -->
<%-- 		<c:forEach var="anOrderBean" varStatus="stat" items="${memberOrders}"> --%>
<!-- 			<TR id='borderA' height='30'> -->
<!-- 			<TD id='borderA' width="86" align="center"> -->
<%-- 			    <a  href='<c:url value='orderDetail.do?memberId=${LoginOK.memberId}&orderNo=${anOrderBean.orderNo}' />'> --%>
<%-- 				    ${anOrderBean.orderNo} --%>
<!-- 			    </a> -->
<!-- 			</TD> -->
<%-- 			<TD id='borderA' width="100" align="center">${anOrderBean.orderDate}</TD> --%>
<%-- 			<TD id='borderA' width="80" align="right">${anOrderBean.totalAmount}</TD> --%>
<%-- 			<TD id='borderA' width="400" align="left">&nbsp;${anOrderBean.shippingAddress}</TD> --%>
							
<!-- 		</TR> -->
<%-- 		</c:forEach> --%>
<!-- 		<tr height='36' id='borderA'> -->
<%-- 			<td id='borderA' align="center" colspan="4"><a href="<c:url value='../index.jsp' />">回首頁</a></td> --%>
<!-- 		</tr> -->
<!-- 	</TABLE> -->
<%-- 	</center> --%>
</body>
</html>