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
<script type="text/javascript">

function setFocus()
{
     document.getElementById("memberId").focus();
}

function updateOssan() {
    document.forms[0].action="<c:url value=
    	'/_10_personalMaintain/PersonalUpdate.do' />" ;
	document.forms[0].method="POST";
	document.forms[0].submit();
}

</script>

</head>
<body onload="setFocus()">

<!-- 引入共同的頁首 -->  
<jsp:include page="/fragment/top.jsp" />

<div style="padding-left:50px">
  
     <c:set var="name" value='${bean.name}' />
     <c:set var="nickname" value='${bean.nickname}' />
     <c:set var="uid" value='${bean.uniqueId}' />
     <c:set var="city" value='${bean.city}' />
     <c:set var="district" value='${bean.district}' />
     <c:set var="address" value='${bean.address}' />
     <c:set var="tel" value='${bean.phone}' />

     <c:set var="birthday" value='${bean.birthday}' />

<c:if test="${!empty AdminLoginOK}">
	<a class="pure-button pure-button-primary" href="<c:url value=
	'/_20_productMaintain/DisplayMaintainProducts?pageNo=${sessionScope.maintainPageNo}' />">回清單管理</a>
</c:if>

<a class="pure-button pure-button-primary pure-button-disabled">個人基本資料設定</a>

<a class="pure-button pure-button-primary" href="<c:url value=
'/_10_personalMaintain/PersonalPreUpdate2.do' />">格言與自介設定</a>

<a class="pure-button pure-button-primary" href="<c:url value=
'/_10_personalMaintain/PersonalPreUpdate3.do' />">服務地區設定</a>
 
<span class="pure-form-message-inline" id="afterBookInsert">${successMsg.success}${ErrMsg.Exception}</span>
<!-- 為了單人更新新加的 -->
<span class="pure-form-message-inline">${updateSuccess}</span>
<!-- 下面這條不確定是否會使用 -->
<c:remove var="successMsg" scope='session'/>
</div>

<hr>
<form class="pure-form pure-form-aligned" id="form1" name="form1" method="post" 
       action="${pageContext.servletContext.contextPath}/_10_personalMaintain/PersonalUpdate.do"  enctype="multipart/form-data">
    <fieldset>
    
    	<img width='100' 
     src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${bean.ossanNo}&type=OSSAN'>
     
        <div class="pure-control-group">
            <label for="name">姓名</label>
            <input name="name" value="${name}" id="name" type="text">
            <span class="pure-form-message-inline">${ErrMsg.errName}</span>
        </div>

        <div class="pure-control-group">
            <label for="nickname">暱稱</label>
            <input name ="nickname" value="${nickname}" id="nickname" type="text">
            <span class="pure-form-message-inline">${ErrMsg.errNickname}</span>
        </div>
        
        
<!--         PARTIII -->
		<div class="pure-control-group">
            <label for="uid">身分證字號</label>
            <input name ="uid" value="${uid}" id="uid" type="text" >
            <span class="pure-form-message-inline">${ErrMsg.errUid}</span>
        </div>
        
        <div class="pure-control-group">
            <label for="city">城市</label>
            <input name ="city" value="${city}" id="city" type="text" >
            <span class="pure-form-message-inline">${ErrMsg.errCity}</span>
        </div>
        
        <div class="pure-control-group">
            <label for="district">地址</label>
            <input name ="district" value="${district}" id="district" type="text" >
            <span class="pure-form-message-inline">${ErrMsg.errDistrict}</span>
        </div>
        
        <div class="pure-control-group">
            <label for="address">地址</label>
            <input name ="address" value="${address}" id="address" type="text" >
            <span class="pure-form-message-inline">${ErrMsg.errAddress}</span>
        </div>
        
        <div class="pure-control-group">
            <label for="tel">連絡電話</label>
            <input name ="tel" value="${tel}" id="tel" type="text">
            <span class="pure-form-message-inline">${ErrMsg.errTel}</span>
        </div>
        
        <div class="pure-control-group">
        <label for="birthday">出生日期</label>
        <input type="date" id="start" name="birthday" value="${birthday}"
      			 min="1950-01-01" max="2000-12-31">  
        </div>  
        
        <div class="pure-control-group">
        <label for="uploadFile">上傳照片</label>
        <input type="file" name="uploadFile"> 
		</div>

        <input name="pKey" type="hidden" id="pKey" value="${bean.ossanNo}>" />
        
        <div class="pure-controls">
            <button type="submit" name="update" onclick='updateOssan()'
            class="pure-button pure-button-primary">修改
            	</button>
        </div>   
        
    </fieldset>
</form>
<c:remove var="ErrMsg" scope='session'/>

</body>
</html>