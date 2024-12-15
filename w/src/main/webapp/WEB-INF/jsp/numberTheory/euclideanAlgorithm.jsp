<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:if test="${gcdResult!=null}">
    <c:set var="r" value="${gcdResult}" />
    <c:set var="a" value="${r.a}" />
    <c:set var="b" value="${r.b}" />
</c:if>

<H1>Euclidean Algorithm</H1>

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<form action="">
	<span class="wrap"><label for="aId">a</label><input type="text" width="20" maxlength="20" value="${a}" name="a" id="aId"></span> 
	<BR>  
	<span class="wrap"><label for="bId">b</label><input type="text" width="20" maxlength="20" value="${b}" name="b" id="bId"></span> 
	<BR>  
	<button type="submit" value="solve" name="waction">solve</button>
	<button type="submit" value="clear" name="waction">clear</button>
	<button type="submit" value="random" name="waction">random</button>
	<button type="submit" value="exampleA527B341" name="waction">a=527 b=341</button>
</form>
</div>
</div>
<br clear="all" />


<BR>  

<c:if test="${r.completed}">
<c:set var="m" value="${r.a}" />
<c:set var="n" value="${r.b}" />
<c:forEach var="qr" items="${r.qrl}">
\[ ${m} = ${n} \cdot ${qr[0]} + ${qr[1]} \]
<c:set var="l" value="${m}" />
<c:set var="m" value="${n}" />
<c:set var="n" value="${qr[1]}" />
</c:forEach>
\[ \]
<c:forEach var="qr" items="${r.qrlRev}">
\[ ${m} \cdot ${qr[0]} + ${qr[1]} = ${qr[2]} \quad \Rightarrow \quad ${r.gcd}\;\vert\;${qr[2]} \]
<c:set var="m" value="${qr[2]}" />
</c:forEach>
<div class="mexample">
<div class="mbody">
\[ gcd(${r.a},${r.b})=${r.gcd} \]
</div>
</div>
<br clear="all" />
</c:if>