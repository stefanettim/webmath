<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>


<script src="https://cdn.plot.ly/plotly-2.26.0.min.js" charset="utf-8"></script>


<c:set var="r" value="${relativePrimesResult}" />

<div class="mnote">
<div class="mtitle">\[ Euler's\;\phi{-function}\]</div>
<div class="mbody">
\[ \phi(m) \text{ is the number of positive integers} \leq m,\]
\[ \text{that are relative primes to m} \]
</div>
</div>
<br clear="all" />
<div class="mexample">
<div class="mtitle">\[ \text{p prime} \]</div>
<div class="mbody">
\[ \text{p prime} \Rightarrow \phi(p)=p-1 \]
</div>
</div>
<br clear="all" />
<div class="mtheorem">
<div class="mtitle">Theorem : Divisor sum</div>
<div class="mbody">
\[ \sum_{d \vert n} \phi(d) = n \]
</div>
</div>
<br clear="all" />
<div class="mnote">
<div class="mtitle">\[ T_d(n) \]</div>
<div class="mbody">
\[ T_d(n) = \{ m \in \mathbb{N^+} \mid m \le n, gcd(m,n)=d \} \]
</div>
</div>
<br clear="all" />
<div class="mnote">
<div class="mtitle">\[ \text{ Möbius function } \mu(n) \]</div>
<div class="mbody"> <!-- https://www.johndcook.com/blog/2009/09/14/latex-multi-part-definitions/ -->
\[ 
\mu(n) = 
 \left\{
	\begin{array}{ll}
      {1}      &amp; {\mbox{if } {n=1}} \\
      {0}      &amp; {\mbox{if } {a^2 \vert n \mbox{ for some } a > 1} } \\
      {(-1)^r} &amp; {\mbox{if } {n \mbox{ has } r \mbox{ distinct prime factors}} }
	\end{array}
\right.	
\]
</div>
</div>
<br clear="all" />


<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">

<c:set var="n" value="${n}" />

<form action="">
	<button type="submit" value="clear" name="waction">clear</button> 
	<span class="wrap"><label for="nId">n</label><input type="text" width="5" maxlength="10" value="${n}" name="n" id="nId"></span> <BR>
	<button type="submit" value="solve" name="waction">solve</button> 
	<button type="submit" value="solve10" name="waction">n=10</button>
	<button type="submit" value="solve29" name="waction">n=29</button>
	<button type="submit" value="solve100" name="waction">n=100</button>
	<BR>
	<button type="submit" value="plot" name="waction">plot</button>
	<button type="submit" value="plot100" name="waction">n=100</button>
	<button type="submit" value="plot1000" name="waction">n=1000</button>
	<button type="submit" value="plot2000" name="waction">n=2000</button>
	<BR>
	<button type="submit" value="table" name="waction">table</button>
	<button type="submit" value="table20" name="waction">n=20</button>
	<BR>
	<button type="submit" value="divisorSum" name="waction">divisor sum</button>
</form>
</div>
</div>
<br clear="all" />

<BR>

<c:if test="${r.completed}">
\[ \text{relative primes of ${r.n} : } \]
\[ ${r.relativePrimes} \]
\[ \]
\[ \phi(${r.n})=${r.count} \]
</c:if>

<c:if test="${eulerPairs!=null}">
<div id='myDiv'></div>
<script type="text/javascript">
		var ep = {
		  x: [ <c:forEach var="p" items="${eulerPairs}">${p.a},</c:forEach> ],
		  y: [ <c:forEach var="p" items="${eulerPairs}">${p.b},</c:forEach> ],
		  name: 'P(x)'
		};

		var data = [ep];

		Plotly.newPlot('myDiv', data);
		
</script>
</c:if>

<c:if test="${eulerTable!=null}">

<table class="trtab">
<tr class="trhead">
<th>\[ n \]</th>
<th>\[ \text{prime} \]</th>
<th>\[ \phi(n) \]</th>
<th>\[ d(n) \]</th>
<th>\[ \sigma(n) \]</th>
</tr>

<c:forEach var="r" items="${eulerTable}" varStatus="vs">
<tr>
<td>\[ ${r.n} \]</td>
<td>\[ ${r.prime} \]</td>
<td>\[ ${r.relativePrimesResult.count} \]</td>
<td>\[ ${r.divisorsResult.count} \]</td>
<td>\[ ${r.divisorsResult.sum} \]</td>
</tr>
</c:forEach>

</table>

</c:if>

<c:if test="${divisorSumTable!=null}">

<h2>Divisor sum </h2>
\[ n = ${n} \]

<table class="trtab">
<tr class="trhead">
<th>\[ d \]</th>
<th>\[ \text{divisor} \]</th>
<th>\[ \text{relative primes of } d \]</th>
<th>\[ \phi(d) \]</th>
<th>\[ T_d(n) \]</th>
<th>\[ \text{#}\{ T_d(n) \} \]</th>
<th>\[ n/d \]</th>
<th>\[ \text{relative primes of } n/d \]</th>
<th>\[ \phi(n/d) \]</th>
</tr>

<c:forEach var="r" items="${divisorSumTable}" varStatus="vs">
<c:set var="trclass" value="trbad"></c:set>
<c:if test="${r.divisor}">
	<c:set var="trclass" value="trgood"></c:set>
</c:if>
<tr class="${trclass}">
<td>\[ ${r.d} \]</td>
<td>\[ ${r.divisor} \]</td>
<td><c:if test="${r.divisor}">\[ ${r.relativePrimesResult.relativePrimes} \]</c:if></td>
<td><c:if test="${r.divisor}">\[ ${r.relativePrimesResult.count} \]</c:if></td>
<td><c:if test="${r.divisor}">\[ ${r.td} \]</c:if></td>
<td><c:if test="${r.divisor}">\[ ${r.tdCount} \]</c:if></td>
<td><c:if test="${r.divisor}">\[ ${r.nOverD} \]</c:if></td>
<td><c:if test="${r.divisor}">\[ ${r.relativePrimesResultNOverD.relativePrimes} \]</c:if></td>
<td><c:if test="${r.divisor}">\[ ${r.relativePrimesResultNOverD.count} \]</c:if></td>
</tr>
</c:forEach>

<tr>
<th>\[ \text{ Totals } \]</th>
<th></th>
<th></th>
<th>\[ ${divisorSumTtot} \]</th>
<th></th>
<th>\[ ${divisorTdCountTot} \]</th>
<th></th>
<th></th>
<th>\[ ${divisorPhiNOverDTot} \]</th>
<tr>
</table>

</c:if>
