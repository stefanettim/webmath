<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="e" value="${requestScope.exception}"/>

<h1>${errorMessage}</h1>

<div class="code">
<B>Message: ${e.message}</B>
<B>StackTrace:</B>
<%
Exception e = (Exception) request.getAttribute("exception");
if(e!=null) e.printStackTrace(new java.io.PrintWriter(out)); 
%>
</div>

