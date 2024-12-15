<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:if test="${powerResult!=null}">
    <c:set var="r" value="${powerResult}" />
</c:if>

<H1>Powers modulo m</H1>

<div class="mexample">
<div class="mtitle">\[ \text{Search for base} \]</div>
<div class="mbody">
\[ a \cdot x^n \equiv b \pmod m \]
</div>
</div>
<br clear="all" />

<div class="mexample">
<div class="mtitle">\[ \text{Search for exponent} \]</div>
<div class="mbody">
\[ a \cdot n^x \equiv b \pmod m \]
</div>
</div>
<br clear="all" />

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<form action="">
	<span class="wrap"><label for="aId">a</label><input type="text" width="20" maxlength="20" value="${r.a}" name="a" id="aId"></span> 
	<BR>  
	<span class="wrap"><label for="nId">n</label><input type="text" width="20" maxlength="20" value="${r.n}" name="n" id="bId"></span> 
	<BR>  

		<c:if test="${r.exponent}">
		    <c:set var="e1checked" value="" />
		    <c:set var="e2checked" value="checked" />
		</c:if>
		<c:if test="${!r.exponent}">
		    <c:set var="e1checked" value="checked" />
		    <c:set var="e2checked" value="" />
		</c:if>

	Search for 
      <input type="radio" id="exponent1Id" name="e" value="false" ${e1checked} />
      <label for="exponent1Id">Base</label>
      <input type="radio" id="exponent2Id" name="e" value="true"  ${e2checked} />
      <label for="exponent2Id">Exponent</label>

	<BR>  
	<span class="wrap"><label for="bId">b</label><input type="text" width="20" maxlength="20" value="${r.b}" name="b" id="cId"></span> 
	<BR>  
	<span class="wrap"><label for="mId">m</label><input type="text" width="20" maxlength="20" value="${r.m}" name="m" id="mId"></span> 
	<BR>  
	<button type="submit" value="solve" name="waction">solve</button>
	<button type="submit" value="clear" name="waction">clear</button>
	<BR>  
	Search base 
	<button type="submit" value="e1" name="waction">a=3 n=14 b=2 m=23</button>
	<button type="submit" value="e2" name="waction">a=1 n=3 b=5 m=17</button>
	<BR>  
	Search exponent
	<button type="submit" value="e3" name="waction">a=1 n=13 b=5 m=23</button>
	<button type="submit" value="e4" name="waction">a=1 n=5 b=17 m=19</button>
</form>
</div>
</div>
<br clear="all" />


<BR>  

<c:if test="${r.completed}">

<h2>Solution</h2>

<c:if test="${!r.exponent}">
\[ \text{Search base : } {${r.a}} \cdot x^{${r.n}} \equiv {${r.b}} \pmod {${r.m}} \]
</c:if>

<c:if test="${r.exponent}">
\[ \text{Search exponent} \]
\[ {${r.a}} \cdot {${r.n}}^x \equiv {${r.b}} \pmod {${r.m}} \]
</c:if>
<BR>
\[ \text{solutions:} \]
\[ ${r.solutions} \]

<c:if test="${r.solutions.size()==0}">
\[ \text{no solution found} \]
</c:if>


</c:if>