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
<c:set var="funcName" value="ORD" scope="session"/>

<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/top.jsp" />


	<table class="minimalistBlack">
	    <thead>
	        <tr>
	        	<th>項次</th>
	          

	            <th>大叔編號</th>
	            <th>大叔姓名</th>
	            <th>大叔暱稱</th>
	            
	            <th>時數</th>

	            
	        </tr>
	    </thead>
	
	    <tbody>
	    	<c:forEach varStatus="stVar"  var="aDetailOrder"  items="${OrderBean.orderItemBean}" >
		        <tr style="height:20px">
		            <td>${stVar.index+1}</td>
		            
		            
		            <td>${aDetailOrder.ossanBean.ossanNo}</td>
		            <td>${aDetailOrder.ossanBean.name}</td>
		            <td>${aDetailOrder.ossanBean.nickname}</td>
		            <td>${aDetailOrder.quantity}</td>
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