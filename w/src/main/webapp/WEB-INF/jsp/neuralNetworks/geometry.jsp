<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isErrorPage="true"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<STYLE>

DIV.geometryGrid {
	  display: grid;
	  grid-template-columns: auto auto auto auto auto auto auto auto auto auto;
	}


DIV.geometry_true, DIV.geometry_false {
 margin:1px;
 padding: 2px;
 border:1px solid black;
 border-color: #DDD;
}

DIV.geometry_false {
 background-color: red;
}

</STYLE>


<table class="customTable">
<tr><th>Correct</th><td>${geometrySamplesCorrect}</td></tr>
<tr><th>Wrong</th><td>${geometrySamplesWrong}</td></tr>
<tr><th>Total</th><td>${geometrySamplesTot}</td></tr>
</table>

<div class="geometryGrid">

<c:forEach var="sample" items="${sessionScope.geometrySamples}" varStatus="vs">

<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div class="geometry_${sample.correct}">
<img id="sampleId" src="${ctx}/i/geometryImage?sample=${sample.id}" width="30" height="30"/>
${sample.guessed}
</div>

</c:forEach>


</div>
