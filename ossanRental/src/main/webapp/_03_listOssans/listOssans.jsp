<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>顯示商品資訊</title>
<style type="text/css">
#quote {
	white-space: pre-wrap
}
</style>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		stage = 1;
		var wsLoc = "ws://127.0.0.1:8080/ossanRental/index.do";
		var ws = new WebSocket(wsLoc);
		var socketMsg = $('#socketArea').text();
		ws.onopen = function() {
			console.log("Websocket is opened!!");
		};
		ws.onmessage = function(msgEvent) {
			console.log("Received message!!", msgEvent);
// 			$("#socketMessage").text(msgEvent.data);
			$("#socketArea").attr('placeholder',msgEvent.data)
			stage = 2;
		}
		ws.onerror = function(evt) {
			console.log("ERR: " + evt.data);
			stage = 1;
		};
		$('#socketButton').click(function(){
			if(stage == 1){
				alert();
			}else{
				ws.send('socketMsg');
			}
		});
		
	});
</script>
</head>

<body>
	<div id="socketMessage" style="color: blue; padding: 15px;position:fixed;right:0px;top:300px;width:300px;height:150px;background-color:#ddd;color:000;">
		<textarea rows="5" cols="10" id="socketArea" style="resize: false;" ></textarea>
		<button id="socketButton">送出</button>	
	</div>

	<jsp:include page="/fragment/top.jsp" />
	<div style="padding-left: 50px">

		<a class="pure-button pure-button-primary"
			href="<c:url value=
				'/_03_listOssans/DisplayOssanProducts?requestArea=twAll' />">全部</a>
		<a class="pure-button pure-button-primary"
			href="<c:url value=
				'/_03_listOssans/DisplayOssanProducts?requestArea=twNorth' />">北部</a>
		<a class="pure-button pure-button-primary"
			href="<c:url value=
				'/_03_listOssans/DisplayOssanProducts?requestArea=twMiddle' />">中部</a>
		<a class="pure-button pure-button-primary"
			href="<c:url value=
				'/_03_listOssans/DisplayOssanProducts?requestArea=twSouth' />">南部</a>
		<a class="pure-button pure-button-primary"
			href="<c:url value=
				'/_03_listOssans/DisplayOssanProducts?requestArea=twOther' />">其他</a>

		<c:choose>

			<c:when test="${ShoppingCart.itemNumber > 0}">
				<!-- 購物車內有一項以上的商品 -->
				<c:set var="cartContent1" value="購物車內有${ShoppingCart.itemNumber}項商品" />
				<span class="pure-form-message-inline">${cartContent1}</span>
				<span class="pure-form-message-inline">金額小計(OK):<c:out
						value="${ShoppingCart.subtotal}" default="0" /> 元
				</span>
			</c:when>

			<c:otherwise>
				<span class="pure-form-message-inline"></span>
			</c:otherwise>

		</c:choose>
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
				<th>看個人頁面</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach varStatus="stVar" var="aOssanBean" items="${products_DPP}">

				<tr>
					<td>${aOssanBean.ossanNo}</td>
					<td><img width='100'
						src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${aOssanBean.ossanNo}&type=OSSAN'>
					</td>
					<td>${aOssanBean.name}</td>
					<td>${aOssanBean.nickname}</td>
					<!-- 			Jay:注意這邊的用法，可以在迴圈裡用兩種變數 -->
					<td id="quote">${aOssanBean.quote}</td>

					<td>
						<button class="button-small pure-button "
							onclick="javascript:location.href=
             	'DisplayOneProduct.do?pKey=${aOssanBean.ossanNo}&pageNo=${pageNo}'">個人頁面</button>
					</td>

				</tr>
			</c:forEach>

			<tr>
				<td><c:if test="${pageNo > 1}">
						<div id="pfirst">
							<a
								href="<c:url value='DisplayOssanProducts?
		              pageNo=1&requestArea=${sessionScope.sessionArea}' />">第一頁</a>
						</div>
					</c:if></td>
				<td><c:if test="${pageNo > 1}">
						<div id="pprev">
							<a
								href="<c:url value='DisplayOssanProducts?pageNo=${pageNo-1}&requestArea=${sessionScope.sessionArea}' />">上一頁</a>
						</div>
					</c:if></td>
				<td><c:if test="${pageNo != totalPages}">
						<div id="pnext">
							<a
								href="<c:url value='DisplayOssanProducts?pageNo=${pageNo+1}&requestArea=${sessionScope.sessionArea}'/>">下一頁</a>
						</div>
					</c:if></td>
				<td><c:if test="${pageNo != totalPages}">
						<div id="plast">
							<a
								href="<c:url value='DisplayOssanProducts?pageNo=${totalPages}&requestArea=${sessionScope.sessionArea}' />">最末頁</a>
						</div>
					</c:if></td>
				<td>第${pageNo}頁 / 共${totalPages}頁</td>

				<td></td>

			</tr>
		</tbody>
	</table>
</body>
</html>