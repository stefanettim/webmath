<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:include  page="numberTheory/index.jsp" />
<jsp:include  page="math/index.jsp" />
<jsp:include  page="groups/index.jsp" />
<jsp:include  page="games/index.jsp" />
<jsp:include  page="neuralNetworks/index.jsp" />


