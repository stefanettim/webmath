<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<fmt:formatDate value="${requestScope.startTime}" pattern="yyyyMMdd" var="wdate"/>

<link rel="stylesheet" href="${ctx}/css/math.css?date=${wdate}">
<script src="${ctx}/js/knot.js"></script>

<script type="text/javascript">
function updateSlider(rp) {
	var display = document.getElementById("chosen");
	display.innerHTML="r="+rp;
	document.getElementById("knotId").src="${ctx}/i/knotImage?rp="+rp;
}
</script>

<div id="mainknot">
	<div id="slider">1 <input id="slide" type="range" min="1" max="50" step="1" value="20" onchange="updateSlider(this.value)"/> 50</div>
	<div id="chosen">r=23</div>
	<img id="knotId" src="${ctx}/i/knotImage?rp=23"/>
</div>
