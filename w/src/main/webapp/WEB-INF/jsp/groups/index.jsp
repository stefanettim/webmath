<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<H1>Groups</H1>
<div class="button-container">
<a href="${ctx}/a/groups/subgroups?waction=Q4" class="button">Q4 subgroups</a>
<a href="${ctx}/a/groups/C3" class="button">C3 Plot</a>
</div>
