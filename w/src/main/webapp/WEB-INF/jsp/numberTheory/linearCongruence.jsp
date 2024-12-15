<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:if test="${linearCongruenceResult!=null}">
	<c:set var="a" value="${linearCongruenceResult.a}" />
	<c:set var="b" value="${linearCongruenceResult.b}" />
	<c:set var="c" value="${linearCongruenceResult.c}" />
</c:if>

<div class="mtheorem">
<div class="mtitle">Linear Congruence</div>
<div class="mbody">
\[ a \cdot n \equiv b \pmod c \]
</div>
</div>
<br clear="all" />

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<form action="">
	<span class="wrap"><label for="aId">a</label><input type="text" width="20" maxlength="20" value="${a}" name="a" id="aId"></span> <BR>
	<span class="wrap"><label for="bId">b</label><input type="text" width="20" maxlength="20" value="${b}" name="b" id="bId"></span> <BR>
	<span class="wrap"><label for="cId">c</label><input type="text" width="20" maxlength="20" value="${c}" name="c" id="cId"></span> <BR>
	
	<button type="submit" value="solve" name="waction">solve</button>
	<button type="submit" value="clear" name="waction">clear</button>
	<BR>
	<button type="submit" value="solveA5B3C8" name="waction">a=5 b=3 c=8</button>
	<button type="submit" value="solveA21B11C3" name="waction">a=21 b=11 c=3</button>
	<button type="submit" value="solveA15B9C12" name="waction">a=15 b=9 c=12</button>
	<button type="submit" value="solveA45B12C2" name="waction">a=4 b=12 c=2</button>
</form>
</div>
</div>
<br clear="all" />

<c:if test="${linearCongruenceResult.completed}">
\[ ${a}\cdot n \equiv ${b} \pmod {${c}} \]
\[ gcd(${a},${c})=${linearCongruenceResult.d}\]
<c:if test="${linearCongruenceResult.solvable}">
\[ ${linearCongruenceResult.d} \vert ${b} \]
\[ \text{the congruence has ${linearCongruenceResult.d} incongruent solutions: } \]
\[ ${linearCongruenceResult.incongruentSolutions} \]
</c:if>
<c:if test="${!linearCongruenceResult.solvable}">
\[ ${linearCongruenceResult.d} \nmid ${b} \]
\[ \text{the congruence has no solutions} \]
</c:if>
<br clear="all" />
</c:if>

