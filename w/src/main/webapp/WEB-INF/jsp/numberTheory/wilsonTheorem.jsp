<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="r" value="${wilsonTheoremResult}" />

<div class="mtheorem">
<div class="mtitle">Wilson's Theorem</div>
<div class="mbody">
\[ (p-1)! \equiv -1\pmod p\quad\quad\Leftrightarrow\quad\quad\text{p is prime} \]
</div>
</div>
<br clear="all" />

<c:if test="${results==null}">
<div class="mform">
<div class="mtitle">Primes</div>
<div class="mbody">
		<c:forEach var="p" items="${primes}" varStatus="ps">
			<c:if test="${p>0}">
				<a href="?waction=solve&p=${p}">${p}</a>
			</c:if>
		</c:forEach>
</div>
</div>
<br clear="all" />
</c:if>

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<c:if test="${r!=null}">
	<c:set var="p" value="${r.p}" />
</c:if>
<form action="">
	<span class="wrap"><label for="pId">p</label><input type="text" width="20" maxlength="20" value="${p}" name="p" id="pId"></span><br> 
	<button type="submit" value="solve" name="waction">solve</button> 
	<button type="submit" value="clear" name="waction">clear</button> 
	<button type="submit" value="solveP11" name="waction">p=11</button>
	<button type="submit" value="solveP13" name="waction">p=13</button>
	<button type="submit" value="examples" name="waction">examples</button> 
</form>
</div>
</div>
<br clear="all" />

<c:if test="${r!=null}">
<c:if test="${r.prime}">
\[p=${r.p}\]
\[\text{${r.p} is prime}\]
\[\]
\[1\cdot${r.p-1}\equiv-1\pmod {${r.p}} \]
<c:if test="${r.p>3}"> 
\[\]
\[ 
<c:forEach var="pair" items="${r.pairing}" varStatus="vs">
	  <c:if test="${vs.index%10==0 && vs.index>0}">
	  	\]\[
	  </c:if>
${pair.a}\cdot${pair.b}\equiv
</c:forEach>1\pmod {${r.p}} \]
</c:if>
\[\]
	<div class="mexample">
	<div class="mbody">
	\[ (${r.p}-1)! \equiv -1\pmod{${r.p}}\]
	</div>
	</div>
	<br clear="all" />
</c:if>
<c:if test="${!r.prime}">
<div class="mremark">
<div class="mbody">
\[ \text{Wilson's theorem is not applicable, ${r.p} is not prime} \]
</div>
</div>
<br clear="all" />
</c:if>
</c:if>

<c:if test="${results!=null}">
<BR>
<table class="trtab">
<tr class="trhead">
<th>\[n\]</th>
<th>\[\Bbb P\]</th>
<th>\[ \text{Pairing}\]</th>
<th>\[(n-1)! \pmod n \]</th>
</tr>
<c:forEach var="r" items="${results}">
<c:set var="trclass" value="trbad"></c:set>
<c:if test="${r.prime}">
	<c:set var="trclass" value="trgood"></c:set>
</c:if>
<tr class="${trclass}">
<td>\[${r.p}\]</td>
<td>
<c:if test="${r.prime}">
\[ \surd \]
</c:if>
<c:if test="${!r.prime}">
\[ \times \]
</c:if>
</td>
<c:if test="${r.prime}">
<td>
<c:if test="${r.p>3}">
	\[ <c:forEach var="pair" items="${r.pairing}" varStatus="vs">
	  <c:if test="${vs.index%4==0&&vs.index>0}">
	  \]\[
	  </c:if>
	  ${pair.a}\cdot${pair.b}\equiv	  
	</c:forEach>1 \pmod {${r.p}}
	 \]
</c:if>
</td>
<td>\[${r.f}\equiv${r.r}\equiv-1 \pmod {${r.p}} \]</td>
</c:if>
<c:if test="${!r.prime}">
<td></td>
<td></td>
</c:if>
</tr>
</c:forEach>
</table>
</c:if>



