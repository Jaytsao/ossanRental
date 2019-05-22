<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html>
<html>
<title>大叔故事館</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/w4.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}

#sArticle { white-space:pre-wrap }

</style>
<body class="w3-light-grey">
<jsp:include page="/fragment/top.jsp" />

<!-- w3-content defines a container for fixed size centered content, 
and is wrapped around the whole page content, except for the footer in this example -->
<div class="w3-content" style="max-width:1400px">

<!-- Header -->
<header class="w3-container w3-center w3-padding-32"> 
<!--   <h1><b>大叔故事館</b></h1> -->
  <p>這裡是我們和所有委託人的 <span class="w3-tag">回憶</span></p>
</header>

<!-- Grid -->
<div class="w3-row">

<!-- Blog entries -->
<div class="w3-col l8 s12">
  <!-- Blog entry -->
  <c:forEach varStatus="stVar"  var="aOssanBean"  items="${products_DPP}" >
  
  <div class="w3-card-4 w3-margin w3-white">
    <img src="${pageContext.servletContext.contextPath}/_00_init/getImage?id=${aOssanBean.artNo}&type=article" alt="Nature" style="width:100%">
    <div class="w3-container">
      <h3><b>${aOssanBean.title}</b></h3>
      <h5>by   ${aOssanBean.ossanbean.name} <span class="w3-opacity"><small>${aOssanBean.updateTime}</small></span></h5>
    </div>

    <div class="w3-container">
      <p id="sArticle">${aOssanBean.sArticle}</p>
      <div class="w3-row">
        <div class="w3-col m8 s12">
          <p><button class="w3-button w3-padding-large w3-white w3-border"><b>READ MORE »</b></button></p>
        </div>
        <div class="w3-col m4 w3-hide-small">
          <p><span class="w3-padding-large w3-right"><b>Comments  </b> <span class="w3-tag">0</span></span></p>
        </div>
      </div>
    </div>
  </div>
  <hr>
  
  </c:forEach>

<!--   <!-- Blog entry -->
<!--   <div class="w3-card-4 w3-margin w3-white"> -->
<!--   <img src="/w3images/bridge.jpg" alt="Norway" style="width:100%"> -->
<!--     <div class="w3-container"> -->
<!--       <h3><b>BLOG ENTRY</b></h3> -->
<!--       <h5>Title description, <span class="w3-opacity">April 2, 2014</span></h5> -->
<!--     </div> -->

<!--     <div class="w3-container"> -->
<!--       <p>Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt sed -->
<!--         tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p> -->
<!--       <div class="w3-row"> -->
<!--         <div class="w3-col m8 s12"> -->
<!--           <p><button class="w3-button w3-padding-large w3-white w3-border"><b>READ MORE »</b></button></p> -->
<!--         </div> -->
<!--         <div class="w3-col m4 w3-hide-small"> -->
<!--           <p><span class="w3-padding-large w3-right"><b>Comments  </b> <span class="w3-badge">2</span></span></p> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- END BLOG ENTRIES -->
</div>

<!-- Introduction menu -->
<div class="w3-col l4">
  <!-- About Card -->
  <h3><b>  &nbsp&nbsp推薦文章</b></h3>
  <c:forEach varStatus="stVar"  var="aOssanBean"  items="${products_DPP}" >
  
   <c:if test="${stVar.index == 1}" >
                         
  <div class="w3-card w3-margin w3-margin-top">
  
  <img src="${pageContext.servletContext.contextPath}/_00_init/getImage?id=${aOssanBean.artNo}&type=article" style="width:100%">
    <div class="w3-container w3-white">
      <h4><b>by   ${aOssanBean.ossanbean.name}</b></h4>
      <p>${aOssanBean.title}</p>
    </div>
  </div><hr>
  	</c:if>
    </c:forEach>
  
  <!-- Posts -->
<!--   <div class="w3-card w3-margin"> -->
<!--     <div class="w3-container w3-padding"> -->
<!--       <h4>Popular Posts</h4> -->
<!--     </div> -->
<!--     <ul class="w3-ul w3-hoverable w3-white"> -->
<!--       <li class="w3-padding-16"> -->
<!--         <img src="/w3images/workshop.jpg" alt="Image" class="w3-left w3-margin-right" style="width:50px"> -->
<!--         <span class="w3-large">Lorem</span><br> -->
<!--         <span>Sed mattis nunc</span> -->
<!--       </li> -->
<!--       <li class="w3-padding-16"> -->
<!--         <img src="/w3images/gondol.jpg" alt="Image" class="w3-left w3-margin-right" style="width:50px"> -->
<!--         <span class="w3-large">Ipsum</span><br> -->
<!--         <span>Praes tinci sed</span> -->
<!--       </li>  -->
<!--       <li class="w3-padding-16"> -->
<!--         <img src="/w3images/skies.jpg" alt="Image" class="w3-left w3-margin-right" style="width:50px"> -->
<!--         <span class="w3-large">Dorum</span><br> -->
<!--         <span>Ultricies congue</span> -->
<!--       </li>    -->
<!--       <li class="w3-padding-16 w3-hide-medium w3-hide-small"> -->
<!--         <img src="/w3images/rock.jpg" alt="Image" class="w3-left w3-margin-right" style="width:50px"> -->
<!--         <span class="w3-large">Mingsum</span><br> -->
<!--         <span>Lorem ipsum dipsum</span> -->
<!--       </li>   -->
<!--     </ul> -->
<!--   </div> -->
<!--   <hr>  -->
 
  <!-- Labels / tags -->
<!--   <div class="w3-card w3-margin"> -->
<!--     <div class="w3-container w3-padding"> -->
<!--       <h4>Tags</h4> -->
<!--     </div> -->
<!--     <div class="w3-container w3-white"> -->
<!--     <p><span class="w3-tag w3-black w3-margin-bottom">Travel</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">New York</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">London</span> -->
<!--       <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">IKEA</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">NORWAY</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">DIY</span> -->
<!--       <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">Ideas</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">Baby</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">Family</span> -->
<!--       <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">News</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">Clothing</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">Shopping</span> -->
<!--       <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">Sports</span> <span class="w3-tag w3-light-grey w3-small w3-margin-bottom">Games</span> -->
<!--     </p> -->
<!--     </div> -->
<!--   </div> -->
  
<!-- END Introduction Menu -->
</div>

<!-- END GRID -->
</div><br>

<!-- END w3-content -->
</div>

<!-- Footer -->
<footer class="w3-container w3-dark-grey w3-padding-32 w3-margin-top">

	<c:if test="${pageNo_li > 1}">
		<button 
		class="w3-button w3-black w3-padding-large w3-margin-bottom"
		onclick='location.href="<c:url value='listArticle_visitor?pageNo_li=1' />"'>
		第一頁</button>
	</c:if>
	
	<c:if test="${pageNo_li > 1}">
		<button 
		class="w3-button w3-black w3-padding-large w3-margin-bottom"
		onclick='location.href="<c:url value='listArticle_visitor?pageNo_li=${pageNo_li-1}' />"'>
		上一頁</button>
	</c:if>
	
	<c:if test="${pageNo_li != totalPages_li}">
		<button 
		class="w3-button w3-black w3-padding-large w3-margin-bottom"
		onclick='location.href="<c:url value='listArticle_visitor?pageNo_li=${pageNo_li+1}' />"'>
		下一頁</button>
	</c:if>
	
	<c:if test="${pageNo_li != totalPages_li}">
		<button 
		class="w3-button w3-black w3-padding-large w3-margin-bottom"
		onclick='location.href="<c:url value='listArticle_visitor?pageNo_li=${totalPages_li}' />"'>
		最末頁</button>
	</c:if>

  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

</body>
</html>
