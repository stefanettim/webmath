<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:if test="${primitiveRootsResult!=null}">
    <c:set var="r" value="${primitiveRootsResult}" />
</c:if>

<H1>Primitive Roots</H1>

<div class="mexample">
<div class="mtitle">Primitive Roots</div>
<div class="mbody">
\[ x^{\phi(m)} \equiv 1 \pmod m \]
</div>
</div>
<br clear="all" />

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<form action="">
	<span class="wrap"><label for="nId">n</label><input type="text" width="20" maxlength="20" value="${r.n}" name="n" id="bId"></span> 
	<BR>  
	<button type="submit" value="solve" name="waction">solve</button>
	<button type="submit" value="clear" name="waction">clear</button>
	<BR>  
	<button type="submit" value="e1" name="waction">n=5</button>
	<button type="submit" value="e2" name="waction">n=9</button>
	<button type="submit" value="e3" name="waction">n=11</button>
	<button type="submit" value="e4" name="waction">n=13</button>
	<button type="submit" value="e5" name="waction">n=15</button>
	<button type="submit" value="e6" name="waction">n=31</button>
	<button type="submit" value="e7" name="waction">n=50</button>
	<BR>  
	<button type="submit" value="pr1" name="waction">n=2 (2)</button>
	<button type="submit" value="pr2" name="waction">n=4 (4)</button>
	<button type="submit" value="pr3" name="waction">n=7 (p^r)</button>
	<button type="submit" value="pr4" name="waction">n=18 (2 p^r)</button>
	<button type="submit" value="pr5" name="waction">n=25 (p^r)</button>
	<button type="submit" value="pr6" name="waction">n=54 (2 p^r)</button>
</form>
</div>
</div>
<br clear="all" />

<BR>  

<c:if test="${r.completed}">

<h2>Primitive Roots</h2>


<BR>
\[ \phi(${r.n}) = ${r.phiOfN} \]
\[ \phi(\phi(${r.n})) = \phi(${r.phiOfN}) = ${r.phiOfPhi}  \]
\[ \text{ expected roots count (if any) : } ${r.phiOfPhi} \]
\[ \text{ expected roots  : } ${r.expectedRoots} \]

<BR>
\[ \text{primitive roots of } ${r.n} \]
\[ ${r.roots} \]
<c:if test="${r.roots.size()==0}">
\[ \text{no primitive root found} \]
</c:if>
<c:if test="${r.roots.size()!=0}">
\[ ${r.roots.size()} \text{ primitive roots found} \]
</c:if>

<BR>


<table class="trtab">
<tr class="trhead">
<th>\[ a^b \pmod {${r.n}} \]</th>
<c:forEach begin="1" end="${r.n}" var="i">
<th>\[ ${i} \]</th>
</c:forEach>
</tr>

<c:forEach var="l" items="${r.powersResult}" varStatus="vs">

<c:set var="trclass" value=""></c:set>
<c:if test="${r.isPrimitiveRoot(l.a)}">
	<c:set var="trclass" value="trfound"></c:set>
</c:if>

<tr class="${trclass}">
<th>\[ ${l.a} \]</th>
<c:forEach var="p" items="${l.powers}" varStatus="vs">
<c:set var="tdclass" value=""></c:set>
<c:if test="${p==1}">
	<c:set var="tdclass" value="tdgood"></c:set>
</c:if>
<c:if test="${p==(l.n-1)}">
	<c:set var="tdclass" value="tdbad"></c:set>
</c:if>
<td class="${tdclass}">\[ ${p} \]</td>
</c:forEach>

</c:forEach>

</table>

<BR>
\[ ${r.n-1} \equiv -1 \pmod {${r.n}} \]

</c:if>