<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<H1>Number Theory - Mixed Results</H1>

<c:set var="n" value="1000" />
<c:if test="${t!=null}">
	<c:set var="n" value="${t.n}" />
</c:if>
<c:if test="${requestScope.n!=null}">
	<c:set var="n" value="${requestScope.n}" />
</c:if>

<form action="">
<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
	<span class="wrap"><label for="nId">max</label><input type="text" width="20" maxlength="20" value="${n}" name="n" id="nId"></span>
	<br>
	<button type="submit" value="set10" name="waction">10</button>
	<button type="submit" value="set100" name="waction">100</button>
	<button type="submit" value="set200" name="waction">200</button>
	<button type="submit" value="set500" name="waction">500</button>
	<button type="submit" value="set1000" name="waction">1,000</button>
	<button type="submit" value="set10000" name="waction">10,000</button>
</div>
</div>
<br clear="all" />
	
	<b>Divisors</b>
	<button type="submit" value="divisors" name="waction">divisors</button>
	<button type="submit" value="perfectNumbers" name="waction">perfect numbers</button>
	<br>
	<b>Primes</b>
	<button type="submit" value="primes" name="waction">primes</button>
	<button type="submit" value="relativePrimes" name="waction">relative primes</button>
	<br>
	<b>Residue</b>
	<button type="submit" value="listQuadraticResidueModp" name="waction">quadratic residue modulo p</button>
	<BR>  
	<b>Sum of perfect squares</b>
	<button type="submit" value="sumPerfectSquares1or2" name="waction">one or two</button>
	<button type="submit" value="sumPerfectSquares1or2or3" name="waction">one, two or three</button>
	<button type="submit" value="sumPerfectSquares1or2or3or4" name="waction">one, two, three or four</button>
	<BR>  
	<b>Sum of positive integers</b>
	<button type="submit" value="sumDistinctPositiveIntegers" name="waction">distinct</button>
	<button type="submit" value="sumOddPositiveIntegers" name="waction">odd</button>
	<button type="submit" value="sumPowerOf2PositiveIntegers" name="waction">power of 2</button>
	<button type="submit" value="sumNotMultipleOf3PositiveIntegers" name="waction">not multiple of 3</button>
	<button type="submit" value="sumMax2DistinctPositiveIntegers" name="waction">no more than twice</button>
	<br>
</form>
<BR>  
 
<!-- ${requestScope.waction}, max value ${n} -->
<c:if test="${divisorsResultList!=null}">
<c:if test="${!onlyPerfect}"> 
	<h3>Divisors of n for n&lt;=${n}</h3>
</c:if>
<c:if test="${onlyPerfect}"> 
	<div class="mnote">
	<div class="mtitle">Perfect number</div>
	<div class="mbody">
		positive integer equal to the sum of its positive divisors 
	    <BR>
		\[ \sigma_1(n)=2 \cdot n \]
	</div>
	</div>
	<br clear="all" />
	<BR>
  	<h3>Perfect numbers for n&lt;=${n}</h3>
</c:if>
<br clear="all" />
<c:forEach var="r" items="${divisorsResultList}" varStatus="vs"> 
    divisors(${r.n})=${r.divisors} d(${r.n})=${r.count} &#x3C3;(${r.n})=${r.sum} aliquot sum s(n)=${r.aliquotSum}
    <c:if test="${r.perfect}"><B>PERFECT</B></c:if>
    <BR>
</c:forEach>
</c:if>

<c:if test="${primes!=null}">
Primes p for p<=${n}<BR><BR>
&#x3C0;(${n})=${nprimes}
<HR>
<c:forEach var="p" items="${primes}" varStatus="vs"> 
  <c:if test="${p>0}">
    ${p} 
  </c:if>
</c:forEach>
</c:if>

<c:if test="${sumPerfectSquares1or2List!=null}">
Integers n<${n} that are perfect squares or sum of two perfect squares : ${solutions}
<BR>	
(max 3 examples)
<HR>
<c:set var="last" value="0" />
<c:forEach var="s" items="${sumPerfectSquares1or2List}" varStatus="vs"> 
  <c:if test="${last!=s[0]}">
      <c:set var="last" value="${s[0]}" />
      <BR>
      ${s[0]}
  </c:if>
      = ${s[1]}&#xB2;
    <c:if test="${s[2]>0}">
      + ${s[2]}&#xB2;
    </c:if>    
</c:forEach>
</c:if>

<c:if test="${sumPerfectSquares1or2or3List!=null}">
Integers n<${n} that are perfect squares or sum of two or three perfect squares : ${solutions} 
<BR>	
(max 3 examples)
<HR>
<c:set var="last" value="0" />
<c:forEach var="s" items="${sumPerfectSquares1or2or3List}" varStatus="vs"> 
  <c:if test="${last!=s[0]}">
      <c:set var="last" value="${s[0]}" />
      <BR>
      ${s[0]}
  </c:if>
      = ${s[1]}&#xB2;
    <c:if test="${s[2]>0}">
      + ${s[2]}&#xB2;
    </c:if>    
    <c:if test="${s[3]>0}">
      + ${s[3]}&#xB2;
    </c:if>    
</c:forEach>
</c:if>

<c:if test="${sumPerfectSquares1or2or3or4List!=null}">
Integers n<${n} that are perfect squares or sum of two or three or four perfect squares : ${solutions} 
<BR>	
(max 3 examples)
<HR>
<c:set var="last" value="0" />
<c:forEach var="s" items="${sumPerfectSquares1or2or3or4List}" varStatus="vs"> 
  <c:if test="${last!=s[0]}">
      <c:set var="last" value="${s[0]}" />
      <BR>
      ${s[0]}
  </c:if>
      = ${s[1]}&#xB2;
    <c:if test="${s[2]>0}">
      + ${s[2]}&#xB2;
    </c:if>    
    <c:if test="${s[3]>0}">
      + ${s[3]}&#xB2;
    </c:if>    
    <c:if test="${s[4]>0}">
      + ${s[4]}&#xB2;
    </c:if>    
</c:forEach>
</c:if>

<c:if test="${listQRMP!=null}">
Quadratic Residue Modulo p : exists m such that p|m^2-n<BR>
List of Quadratic Residue Modulo p for n<=${n}
<HR>
<c:forEach var="qrmp" items="${listQRMP}" varStatus="vs"> 
p=${qrmp.p} QRM=${qrmp.qrm} <BR>
 Consecutives=[
 <c:forEach var="c" items="${qrmp.consecutives}" varStatus="cs">
   <c:if test="${cs.index>0}">,</c:if>
   (${c-1},${c})
 </c:forEach> 
 ] size=${fn:length(qrmp.consecutives)} p/4=${qrmp.p/4}
<BR>
<BR>
</c:forEach>
</c:if>

<c:if test="${listSumPositiveIntegers!=null}">
List of ways an integer n<=${n} can be represented as sum of ${sumPositiveIntegersMessage} positive integers.
<HR>
<c:forEach var="spi" items="${listSumPositiveIntegers}" varStatus="vs"> 
ways(${spi.n})
<c:forEach var="way" items="${spi.ways}" varStatus="cs">
=
<c:set var="plus" value="" />
<c:forEach var="w" items="${way}" varStatus="ws"><c:forEach begin="1" end="${w}" var="c" varStatus="sc">${plus}${spi.values[ws.index]}<c:set var="plus" value="+"/></c:forEach></c:forEach>
</c:forEach>
 total=${ spi.solutions } 
<BR>
</c:forEach>
</c:if>

<c:if test="${relativePrimesResults!=null}">
<BR>
Relative primes of n for n<=${n}
<HR>
<c:forEach var="rpr" items="${relativePrimesResults}" varStatus="vs"> 
relativePrimes(${rpr.n})=${rpr.relativePrimes}
 &#x3A6;(${rpr.n})=${rpr.count}
<BR>
</c:forEach>
</c:if>
