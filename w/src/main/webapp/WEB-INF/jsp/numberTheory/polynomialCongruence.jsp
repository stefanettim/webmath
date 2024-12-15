<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:if test="${polynomialCongruenceResult!=null}">
	<c:set var="r"  value="${polynomialCongruenceResult}" />
</c:if>

<div class="mtheorem">
<div class="mtitle">Lagrange theorem</div>
<div class="mbody">
\[\text{p prime and } p \nmid a_0 \]
\[\]
\[ f(x)=a_0 \cdot x^n + a_1 \cdot x^{n-1} ... + a_{n-1} \cdot x + a_n \]
\[ f(x) \equiv 0 \pmod p \]
\[\]
\[ \text{the congruence has at most} \]
\[ \text{n mutually incongruent solutions modulo p} \]
</div>
</div>
<br clear="all" />

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<form action="">
<table style="width: 10%">
<c:forEach begin="0" end="9" var="n" varStatus="vs">
	<c:if test="${vs.index%5==0}">${"<tr>"}</c:if>
		<td>		
		\[ \; a_{${n}}\]
		</td>
		<td>
			<input id="a${n}Id" type="text" size="2" name="a${n}" />
			<c:if test="${r.a[n]>=0}">
				<script>
				  document.getElementById("a${n}Id").value=${r.a[n]}
				</script>
			</c:if>
		</td>
	<c:if test="${vs.index%5==4}">${"</tr>"}</c:if>
</c:forEach>
	<tr>
	<td>		
	\[ \; m \]
	</td>
	<td>
		<input id="mId" type="text" size="3" name="m" value="${r.m}" />
	</td>
	</tr>
</table>
	<button type="submit" value="solve" name="waction">solve</button>
	<button type="submit" value="clear" name="waction">clear</button>
	<BR>
	<button type="submit" value="example0" name="waction">E0</button>
	<button type="submit" value="example1" name="waction">E1</button>
	<button type="submit" value="example2" name="waction">E2</button>
	<BR>
	<button type="submit" value="exampleGT1a" name="waction">GT1a</button>
	<button type="submit" value="exampleGT1b" name="waction">GT1b</button>
	<button type="submit" value="exampleGT1c" name="waction">GT1c</button>
	<BR>
	<button type="submit" value="euler10" name="waction">Euler m=10</button>
	<button type="submit" value="wilson5" name="waction">Wilson m=5</button>
</form>
</div>
</div>
<br clear="all" />

<c:if test="${r.completed}">
<BR>
\[ \text{degree } ${r.degree} \]

<c:if test="${r.prime}">
\[ \text{m is prime} \]
</c:if>
<c:if test="${!r.prime}">
\[ \text{m is not prime} \]
</c:if>

<c:if test="${r.mdividesa0}">
\[ m \mid a_0 \]
</c:if>
<c:if test="${!r.mdividesa0}">
\[ m \nmid a_0 \]
</c:if>

<c:if test="${r.lagrange}">
\[ \text{Lagrange theorem applies} \]
</c:if>
<c:if test="${!r.lagrange}">
\[ \text{Lagrange theorem does not apply} \]
</c:if>
<BR>

\[ <c:forEach begin="0" end="${r.degree}" var="k"> <c:if test="${r.a[k]>0}">+${r.a[k]} <c:if test="${r.degree-k>0}"> \cdot x^{${r.degree-k}} </c:if></c:if></c:forEach>\equiv 0 \pmod {${r.m}} \]
<BR>

\[ ${r.solutionsCount} \text{ mutually incongruent solutions modulo p:} \]
\[ ${r.incongruentSolutions} \]
</c:if>

