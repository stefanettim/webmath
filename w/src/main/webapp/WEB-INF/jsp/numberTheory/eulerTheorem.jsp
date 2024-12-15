<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="r" value="${eulerTheoremResult}" />

<div class="mtheorem">
<div class="mtitle">Euler's Theorem</div>
<div class="mbody">
\[ gcd(a,m)=1 \quad \Rightarrow \quad a^{\phi(m)} \equiv 1 \pmod m \]
</div>
</div>
<br clear="all" />
<div class="mtheorem">
<div class="mtitle">Fermat's Little Theorem</div>
<div class="mbody">
\[ \text{if m is prime }\quad a^m \equiv a \pmod m \]
</div>
</div>
<br clear="all" />

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">

<c:if test="${r!=null}">
	<c:set var="a" value="${r.a}" />
	<c:set var="m" value="${r.m}" />
</c:if>
<form action="">
	<span class="wrap"><label for="aId">a</label><input type="text" width="20" maxlength="20" value="${a}" name="a" id="aId"></span> <BR>
	<span class="wrap"><label for="mId">m</label><input type="text" width="20" maxlength="20" value="${m}" name="m" id="mId"></span> <BR>
	
	<button type="submit" value="solve" name="waction">solve</button> 
	<button type="submit" value="clear" name="waction">clear</button> 
	<BR>
	<button type="submit" value="solveA3M13" name="waction">a=3 m=13</button>
	<button type="submit" value="solveA7M10" name="waction">a=7 m=10</button>
	<button type="submit" value="solveA21M7" name="waction">a=21 m=7</button>
	<button type="submit" value="solveA12M31" name="waction">a=12 m=31</button>
</form>
</div>
</div>
<br clear="all" />

<c:if test="${r.completed}">
\[ gcd(${r.a},${r.m})=${r.gcd} \]
<c:if test="${r.solvable}">
\[ \text{relative primes } \lt m : \]
\[ ${r.relativePrimesResult.relativePrimes} \]
\[ \phi(m)= ${r.relativePrimesResult.count} \]
<c:if test="${r.primeModule}">
\[ \text{${m} is prime }\Rightarrow \quad \phi(m)=m-1 \]
</c:if>
\[ \text{reduced residue system modulo m :} \]
\[ ${r.relativePrimesResult.reducedResidueSystem} \]  
\[ \text{alternated residue system modulo m :}  \]
\[ ${r.alternatedResidueSystem} \]
\[ \text{pairings :} \]
<c:forEach var="alt" items="${r.alternatedResidueSystem}" varStatus="s">
\[ ${alt} \equiv ${r.relativePrimesResult.reducedResidueSystem[s.index]} \cdot ${r.a} \equiv ${r.pairing[s.index]} \pmod {${r.m}} \]
</c:forEach>
<c:set var="dot" value="0"></c:set>
\[ (<c:forEach var="alt" items="${r.alternatedResidueSystem}" varStatus="s">
<c:if test="${dot==1}">\cdot</c:if><c:set var="dot" value="1"></c:set>
${r.relativePrimesResult.reducedResidueSystem[s.index]}
</c:forEach>) \cdot ${r.a}^{${r.relativePrimesResult.count}} \equiv (
<c:set var="dot" value="0"></c:set>
<c:forEach var="alt" items="${r.alternatedResidueSystem}" varStatus="s">
<c:if test="${dot==1}">\cdot</c:if><c:set var="dot" value="1"></c:set>
${r.pairing[s.index]}
</c:forEach> ) \pmod {${r.m}} \]
<c:set var="dot" value="0"></c:set>
\[ R =<c:forEach var="alt" items="${r.relativePrimesResult.reducedResidueSystem}" varStatus="s">
<c:if test="${dot==1}">\cdot</c:if><c:set var="dot" value="1"></c:set>
${r.pairing[s.index]}
</c:forEach>\]
\[ R \cdot ${r.a}^{${r.relativePrimesResult.count}} \equiv R \pmod {${r.m}} \]
\[ ${r.m} \nmid R \]
<div class="mexample">
<div class="mbody">
\[ ${r.a}^{${r.relativePrimesResult.count}} \equiv 1 \pmod {${r.m}} \]
</div>
</div>
<br clear="all" />
</c:if>

<c:if test="${!r.solvable}">
<div class="mremark">
<div class="mbody">
\[ \text{Euler's theorem is not applicable, gcd must be 1} \]
</div>
</div>
<br clear="all" />
</c:if>

<BR>
<div class="mexample">
<div class="mtitle">Fermat's Little Theorem</div>
<div class="mbody">
<c:if test="${!r.primeModule}">
\[ \text{${m} is not prime} \] 
</c:if>
<c:if test="${r.primeModule}">
\[ \text{${m} is prime} \]
\[ ${a}^{${m}} \equiv ${a} <c:if test="${r.gcd!=1}">\equiv 0</c:if> \pmod {${m}} \]
</c:if>
</div>
</div>
<br clear="all" />



</c:if>

