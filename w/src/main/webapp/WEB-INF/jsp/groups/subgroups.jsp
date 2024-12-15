<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<h1>${group.name}</h1>

<h2>Group</h2>
<table class="w">
<c:forEach var="row" items="${group.table}" >
<TR>
    <c:forEach var="p" items="${row}" >
     <TD>${group.actions[p]}</TD>
    </c:forEach>
</TR>    
</c:forEach>
</table>

<h2>Subgroups</h2>
<c:forEach var="sub" items="${subgroups}" >
  ${sub}<BR>
</c:forEach>
