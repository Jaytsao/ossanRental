<%@page import="_03_listOssans.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
               "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${AppName}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pure-min.css" type="text/css" />

<style>

</style>


<script type="text/javascript">

// function setFocus()
// {
//      document.getElementById("quote").focus();
// }

function updateBook() {
    document.forms[0].action="PersonalUpdate3.do" ;
	document.forms[0].method="POST";
	document.forms[0].submit();
}

function selectAll(){
	 document.getElementById("twNorth").checked = true;
	 document.getElementById("twMiddle").checked = true;
	 document.getElementById("twSouth").checked = true;
	 document.getElementById("twOther").checked = true;
	
}

function UnSelectAll() {
	if (confirm("確定取消所有服務地區 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.getElementById("twNorth").checked = false;
		document.getElementById("twMiddle").checked = false;
		document.getElementById("twSouth").checked = false;
		document.getElementById("twOther").checked = false;
		return;
	} else {
		return;
	}
}

// function UnSelectAll(){
// 	 document.getElementById("twNorth").checked = false;
// 	 document.getElementById("twMiddle").checked = false;
// 	 document.getElementById("twSouth").checked = false;
// 	 document.getElementById("twOther").checked = false;
// }

</script>

</head>
<body>

<!-- 引入共同的頁首 -->  
<jsp:include page="/fragment/top.jsp" />

<div style="padding-left:50px">

<c:if test="${!empty AdminLoginOK}">
	<a class="pure-button pure-button-primary" href="<c:url value=
	'/_20_productMaintain/DisplayMaintainProducts?pageNo=${sessionScope.maintainPageNo}' />">回清單管理</a>
</c:if>

<a class="pure-button pure-button-primary" href="<c:url value=
'/_10_personalMaintain/PersonalPreUpdate.do' />">個人基本資料設定</a>

<a class="pure-button pure-button-primary" href="<c:url value=
'/_10_personalMaintain/PersonalPreUpdate2.do' />">格言與自介設定</a>

<a class="pure-button pure-button-primary pure-button-disabled">服務地區設定</a>

<span class="pure-form-message-inline" id="afterBookInsert">${successMsg.success}${ErrMsg.Exception}</span>
<!-- 為了單人更新新加的 -->
<span class="pure-form-message-inline">${updateSuccess}</span>
<!-- 下面這條不確定是否會使用 -->
<c:remove var="successMsg" scope='session'/>
</div>

<hr>
<form class="pure-form pure-form-aligned" id="form1" name="form1" method="post" 
       action="${pageContext.servletContext.contextPath}/_10_personalMaintain/PersonalUpdate3.do" enctype="multipart/form-data">
    <fieldset>
    
    	<img width='100' 
     src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${OssanSessionPKey}&type=OSSAN'>
     
     
        <div class="pure-control-group">
            <label for="twNorth" class="pure-checkbox">台灣北部</label>
           			<input type="checkbox" name="twNorth" id= "twNorth"
           			       <c:if test='${requestScope.twNorth==true}'>
                  					checked='checked'
               				</c:if>               				          			
           			 value="true" >
        </div>
        
        <div class="pure-control-group">
            <label for="twMiddle" class="pure-checkbox">台灣中部</label>
           			<input type="checkbox" name="twMiddle" id= "twMiddle"
           			       <c:if test='${requestScope.twMiddle==true}'>
                  					checked='checked'
               				</c:if>               				          			
           			 value="true" >
        </div>
        
        <div class="pure-control-group">
            <label for="twSouth" class="pure-checkbox">台灣南部</label>
           			<input type="checkbox" name="twSouth" id= "twSouth"
           			       <c:if test='${requestScope.twSouth==true}'>
                  					checked='checked'
               				</c:if>               				          			
           			 value="true" >
        </div>
        
        <div class="pure-control-group">
            <label for="twOther" class="pure-checkbox">台灣其他</label>
           			<input type="checkbox" name="twOther" id= "twOther"
           			       <c:if test='${requestScope.twOther==true}'>
                  					checked='checked'
               				</c:if>               				          			
           			 value="true" >
        </div>
        
        <input name="pKey" type="hidden" id="pKey" value="${OssanSessionPKey}" />
        
        <div class="pure-controls">
        	
            <button type="submit" name="update" onclick='updateBook()'
            class="pure-button pure-button-primary">送出修改
            	</button>
            
            <button type=button onclick='selectAll()'
            class="pure-button pure-button-primary">選擇全部
            	</button>	
            	
            <button type=button onclick='UnSelectAll()'
            class="pure-button pure-button-primary">取消全部
            	</button>
            	
        </div>
        
        <div class="pure-controls">
            <span class="pure-form-message-inline">若選擇「取消全部」，您的資料將暫時停止出現在網頁</span>
        </div>
        
        <div class="pure-controls">
            
        </div>
        
        
    </fieldset>
</form>
<c:remove var="ErrMsg" scope='session'/>

</body>
</html>