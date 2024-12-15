<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${not empty sessionInfo}">
Session : <%=request.getAttribute("sessionInfo")%>
</c:if>
<c:if test="${empty sessionInfo}">
No session
</c:if>
<BR>
Info: ${applicationScope.mProperties.info}