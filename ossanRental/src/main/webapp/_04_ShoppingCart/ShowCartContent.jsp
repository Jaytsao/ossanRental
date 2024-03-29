<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
    
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=DEL&bookID=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
function modify(key, qty, index) {
	var x = "newQty" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=MOD&bookID=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
      return false;
   }
   return true;
}
function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}
function Abort() {
	if (confirm("確定放棄購物 ? ") ) {
		return true;
	} else {
		return false;
	}
}
</script>

<meta charset="UTF-8">
<title>購物清單</title>
</head>
<body>

<c:set var="funcName" value="CHE" scope="session"/>
<jsp:include page="/fragment/top.jsp" />

<c:choose>
   <c:when test="${ShoppingCart.subtotal > 0}">
      <c:set var="subtotalMessage" value="金額小計:${ShoppingCart.subtotal} 元"/>
      <c:set var="subtotal" value="${ShoppingCart.subtotal}"/>  
   </c:when>
   <c:otherwise>
      <c:set var="subtotalMessage" value="金額小計:  0 元"/>
      <c:set var="subtotal" value="0"/>                
   </c:otherwise>
</c:choose>

<div style="padding-left:50px">
	<span>
		<a class="pure-button pure-button-primary" href="<c:url value='../_03_listOssans/DisplayOssanProducts?pageNo=${sessionScope.pageNo}&requestArea=${sessionScope.sessionArea}' />">繼續購物</a>
	</span>	
		
	<span>
		<a class="pure-button pure-button-primary" href="<c:url value='checkout.do' />" onClick="return Checkout(${subtotal});">再次確認</a>
	</span>
		
	<span>
		<a class="pure-button pure-button-primary" href="<c:url value='abort.do' />" onClick="return Abort();">放棄購物</a>
	</span>	
</div>
<hr>
<div style="padding:50px">
	<table class="pure-table pure-table-bordered">
	    <thead>
	        <tr>
	            <th>項次</th>
	            <th>帳號</th>
	            <th>姓名</th>
	            <th>單價</th>
	            <th>時數</th>
	            <th>小計</th>
	            <th>修改</th>
	        </tr>
	    </thead>
	
	    <tbody>
	    
	    	<c:forEach varStatus="vs"  var="aOssanBean"  items="${ShoppingCart.content}" >
		        <tr>
		            <td>${vs.index+1}</td>
		            <td>${aOssanBean.value.memberId}</td>
		            <td>${aOssanBean.value.name}</td>
		            <td>
		            	<fmt:formatNumber value="${aOssanBean.value.price * aOssanBean.value.discount }" pattern="#,###" />
		            </td>
		            <td>
		            	<div class="pure-control-group">
		            		<Input id="newQty${vs.index}" name="newQty" type="text" value="<fmt:formatNumber value="${aOssanBean.value.qty}" />" name="qty" onkeypress="return isNumberKey(event)"  />
		            	</div>
		            </td>
		            <td>
		            	<fmt:formatNumber value="${aOssanBean.value.price * aOssanBean.value.discount * aOssanBean.value.qty}" pattern="#,###,###" />
		            </td>
		            <td>
		            
		            	<Input type="button" name="update" value="修改" onclick="modify(${aOssanBean.key}, ${aOssanBean.value.qty}, ${vs.index})">
		                <Input type="button" name="delete" value="刪除" onclick="confirmDelete(${aOssanBean.key})">
	            
		            </td>
		        </tr>
	        </c:forEach>
	        	
	        	<tr>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td>合計金額</td>
		        	<td>${ShoppingCart.subtotal}元</td>
		        	<td></td>
	        	</tr>
	        	
	        	<tr>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td>營業稅</td>
		        	<td>
		        		<c:set var="VAT" value="${subtotal*0.05 + 0.0001}"/>
		        		<fmt:formatNumber value="${VAT}" pattern="#,###,###" />元
		        	</td>
		        	<td></td>
	        	</tr>
	        	
	        	<tr>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td>合計金額</td>
		        	<td><fmt:formatNumber value="${subtotal + VAT }" pattern="#,###,###" />元</td>
		        	<td></td>
	        	</tr>
	        
		</tbody>
	</table>
</div>
<!-- <TABLE style="margin: 0 auto; width:820px; background:#EFEFFB; border:2px solid black; "> -->
<!-- <TR><TD colspan='4'> -->
<!-- <!--          購物車的標題          -->  
<!--    <TABLE style="width:820px"> -->
<!--      <TR height='40'> -->
<!--      	<TD width="270">&nbsp;</TD> -->
<%--      	<TD width="280" align='center'><FONT  size='+2'>${AppName}</FONT></TD> --%>
<!--      	<TD width="270" align='right'></TD> -->
<!--      </TR> -->
<!--      <TR height='18'> -->
<!--      	<TD width="270">&nbsp;</TD> -->
<!--      	<TD width="280" align='center'><FONT  size='+2'>購 物 清 單</FONT></TD> -->
<!--      	<TD width="270" align='right'></TD> -->
<!--      </TR> -->
<!-- </TABLE> -->
<!-- </TD></TR> -->

<!-- <TR> -->
<!--    <TD> -->
      
<!--      <TABLE border='1'> -->
<!--      <TR><TH width="320">書籍名稱</TH><TH width="70">作者</TH><TH width="60">出版社</TH><TH width="60">單價</TH><TH width="40">數量</TH><TH width="110">小計</TH><TH width="110">修改</TH></TR> -->
<%--      <c:forEach varStatus="vs" var="anEntry" items="${ShoppingCart.content}"> --%>
<!--         <TR height='16'> -->
<%--           <TD >${anEntry.value.title}</TD> --%>
<%--           <TD style="text-align:center;">${fn:substring(anEntry.value.author, 0, 3)}</TD> --%>
<%--           <TD style="text-align:center;">${fn:substring(anEntry.value.companyName, 0, 2)}</TD> --%>
<%--           <TD style="text-align:right;"><fmt:formatNumber value="${anEntry.value.price * anEntry.value.discount }" pattern="#,###" />元</TD> --%>
<!--           <TD style="text-align:right;"> -->
<%--                 <Input id="newQty${vs.index}" style="width:28px;text-align:right" name="newQty" type="text" value="<fmt:formatNumber value="${anEntry.value.qty}" />" name="qty" onkeypress="return isNumberKey(event)"  /> --%>
<!--           </TD> -->
<%--           <TD style="text-align:right;"><fmt:formatNumber value="${anEntry.value.price * anEntry.value.discount * anEntry.value.qty}" pattern="#,###,###" />元</TD> --%>
<%--           <TD ><Input type="button" name="update" value="修改" onclick="modify(${anEntry.key}, ${anEntry.value.qty}, ${vs.index})"> --%>
<%--                <Input type="button" name="delete" value="刪除" onclick="confirmDelete(${anEntry.key})"></TD> --%>
<!--         </TR> -->
<%--      </c:forEach> --%>
<!--         <TR height='16'> -->
<!--           <TD colspan='5' align='right'>合計金額：</TD> -->
<%--           <TD align='right'><fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</TD> --%>
<!--           <TD align='right'>&nbsp;</TD>           -->
<!--         </TR> -->
<!--         <TR> -->
<!--           <TD colspan='5' align='right'>營業稅：</TD> -->
<%--           <c:set var="VAT" value="${subtotal*0.05 + 0.0001}"/> --%>
<%--           <TD align='right'><fmt:formatNumber value="${VAT}" pattern="#,###,###" />元</TD> --%>
<!--           <TD align='right'>&nbsp;</TD>           -->
<!--         </TR> -->
<!--         <TR> -->
<!--           <TD colspan='5' align='right'>總計金額：</TD> -->
<%--           <TD align='right'><fmt:formatNumber value="${subtotal + VAT }" pattern="#,###,###" />元</TD> --%>
<!--           <TD align='right'>&nbsp;</TD>           -->
<!--         </TR> -->
<!--    </TABLE> -->
   
<!--    </TD> -->
<!-- </TR> -->
<!-- <TR height='80'> -->
<!--    <TD >  -->
<!--      <TABLE border='1'> -->
<!--         <TR > -->
<!--           <TD width="265" align='center'> -->
<%--               <A href="<c:url value='../_03_listBooks/DisplayPageProducts?pageNo=${param.pageNo}' />">繼續購物</A> --%>
<!--           </TD> -->
<!--           <TD width="265" align='center'> -->
<%--               <A href="<c:url value='checkout.do' />" onClick="return Checkout(${subtotal});">再次確認</A> --%>
<!--           </TD> -->
<!--           <TD width="265" align='center'> -->
<%--               <A href="<c:url value='abort.do' />" onClick="return Abort();">放棄購物</A> --%>
<!--           </TD> -->
<!--         </TR> -->
<!--      </TABLE> -->
<!--    </TD> -->
<!-- </TR> -->
<!-- </TABLE> -->
<div style='text-align:center;'>
<c:if test='${not empty OrderErrorMessage}'>
		<font color='red'>${OrderErrorMessage}</font>
		<c:remove var="OrderErrorMessage"/>	
</c:if>
</div>
    
<form>
   <input type="hidden" name="a"/>
</form>

</body>
</html>