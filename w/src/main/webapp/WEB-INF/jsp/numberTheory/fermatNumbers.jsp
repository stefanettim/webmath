<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="mnote">
<div class="mtitle">\[ Fermat's\;numbers\]</div>
<div class="mbody">
\[ F(n)=2^{2^n}+1 \]
</div>
</div>
<br clear="all" />
<BR>

<table class="trtab">
<tr class="trhead">
<th>\[n\]</th>
<th>\[\Bbb P\]</th>
<th>\[ d \vert F(n) \]</th>
<th>\[ F(n)\]</th>
</tr>

<c:forEach var="r" items="${results}" varStatus="vs">

<c:set var="trclass" value="trbad"></c:set>
<c:if test="${r.prime}">
	<c:set var="trclass" value="trgood"></c:set>
</c:if>
<c:if test="${!r.verified}">
	<c:set var="trclass" value="trunknown"></c:set>
</c:if>
<tr class="${trclass}">
<td>\[${r.order}\]</td>
<td>
<c:if test="${r.prime&&r.verified}">
	\[ \surd \]
</c:if>
<c:if test="${!r.prime&&r.verified}">
	\[ \times \]
</c:if>
<c:if test="${!r.verified}">
	\[ ? \]
</c:if>
</td>
<td>
<c:if test="${!r.prime&&r.verified}">
	\[ ${r.divisor} \]
</c:if>
</td>
<td>\[${r.fermatNumber}\]</td>
</tr>

</c:forEach>

</table>


