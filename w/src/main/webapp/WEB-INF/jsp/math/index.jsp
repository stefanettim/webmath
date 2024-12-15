<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<H1>Math</H1>
<div class="button-container">
<a href="${ctx}/a/math/knot" class="button">Knot</a>
<a href="${ctx}/a/math/zpoly" class="button">zPoly</a>
<a href="${ctx}/a/math/isThisAnInteger" class="button">Is this an integer?</a>
</div>
