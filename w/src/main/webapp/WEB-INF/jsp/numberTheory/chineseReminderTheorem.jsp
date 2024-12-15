<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<%@ taglib prefix="sql" uri="jakarta.tags.sql" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:if test="${chineseReminderTheoremResult!=null}">
	<c:set var="r"  value="${chineseReminderTheoremResult}" />
	<c:set var="a1" value="${chineseReminderTheoremResult.a[0]}" />
	<c:set var="b1" value="${chineseReminderTheoremResult.b[0]}" />
	<c:set var="m1" value="${chineseReminderTheoremResult.m[0]}" />
	<c:set var="a2" value="${chineseReminderTheoremResult.a[1]}" />
	<c:set var="b2" value="${chineseReminderTheoremResult.b[1]}" />
	<c:set var="m2" value="${chineseReminderTheoremResult.m[1]}" />
	<c:set var="a3" value="${chineseReminderTheoremResult.a[2]}" />
	<c:set var="b3" value="${chineseReminderTheoremResult.b[2]}" />
	<c:set var="m3" value="${chineseReminderTheoremResult.m[2]}" />
	<c:set var="a4" value="${chineseReminderTheoremResult.a[3]}" />
	<c:set var="b4" value="${chineseReminderTheoremResult.b[3]}" />
	<c:set var="m4" value="${chineseReminderTheoremResult.m[3]}" />
	<c:set var="a5" value="${chineseReminderTheoremResult.a[4]}" />
	<c:set var="b5" value="${chineseReminderTheoremResult.b[4]}" />
	<c:set var="m5" value="${chineseReminderTheoremResult.m[4]}" />
</c:if>

<div class="mtheorem">
<div class="mtitle">Chinese Reminder Theorem</div>
<div class="mbody">
\[ m_1, m_2, \cdots, m_s \in \mathbb{N^+} \]
\[ gcd(m_i,m_j)=1 \quad \forall i,j \quad i \neq j  \]
\[ M=m_1 \cdot m_2 \cdots m_s \]
\[ a_1, a_2, \cdots, a_s \in \mathbb{Z} \]
\[ gcd(a_i,m_i)=1 \quad \forall i \]
\[ \]
\[ a_1 \cdot x \equiv b_1 \pmod {m_1} \]
\[ a_2 \cdot x \equiv b_2 \pmod {m_2} \]
\[ \cdots \]
\[ a_s \cdot x \equiv b_s \pmod {m_s} \]
\[  \]
\[ \text{x exists and is unique modulo M }\]
</div>
</div>
<br clear="all" />

<div class="mform">
<div class="mtitle">Parameters</div>
<div class="mbody">
<form action="">
<table style="width: 100%">
<c:forEach begin="1" end="5" var="n" varStatus="vs">
	<tr>
	<td>
		<c:set var="fields" value='<%=new String[]{"a", "b", "m"} %>'/>
		<c:forEach var="f" items="${fields}" varStatus="vsn">
		<span class="wrap"><label for="${f}${n}Id">${f}${n}</label><input id="${f}${n}Id" type="text" size="3" name="${f}${n}" /></span> 
		</c:forEach>
		<script>
		  <c:if test="${r.a[n-1]>=0}">document.getElementById("a${n}Id").value=${r.a[n-1]}</c:if>
		  <c:if test="${r.b[n-1]>=0}">document.getElementById("b${n}Id").value=${r.b[n-1]}</c:if>
		  <c:if test="${r.m[n-1]>=0}">document.getElementById("m${n}Id").value=${r.m[n-1]}</c:if>
		</script>
	</td>
	<td>\[ a_${n} \cdot x \equiv b_${n} \pmod {m_${n}} \]</td>
	</tr>
</c:forEach>
</table>
<BR>
	<button type="submit" value="solve" name="waction">solve</button>
	<button type="submit" value="clear" name="waction">clear</button>
	<BR>
	<button type="submit" value="example1" name="waction">E1</button>
	<button type="submit" value="example2" name="waction">E2</button>
	<button type="submit" value="bad1" name="waction">Bad1</button>
	<button type="submit" value="bad2" name="waction">Bad2</button>
	<button type="submit" value="example5" name="waction">E5</button>
	<button type="submit" value="example6" name="waction">E6</button>
	<BR>
	<button type="submit" value="examplea" name="waction">Ea</button>
	<button type="submit" value="exampleb" name="waction">Eb</button>
	<button type="submit" value="examplec" name="waction">Ec</button>
	<button type="submit" value="exampled" name="waction">Ed</button>
	<button type="submit" value="examplee" name="waction">Ee</button>
	<button type="submit" value="examplef" name="waction">Ef</button>
</form>
</div>
</div>
<br clear="all" />

<c:if test="${r.completed}">
<BR>

<c:forEach begin="0" end="${r.l-1}" var="k">
<c:if test="${r.a[k]>0}">
\[ ${r.a[k]} \cdot x \equiv ${r.b[k]} \pmod {${r.m[k]}} \]
</c:if>
</c:forEach>
<BR>

<c:if test="${!r.correct}">
\[ \text{Invalid values : ${r.incorrectMessage}} \]
</c:if>

<c:if test="${r.correct}">

\[ \text{size: }${r.l} \]
<BR>
\[ M = ${r.product} \]
<BR>

<c:forEach begin="0" end="${r.l-1}" var="k">
<c:if test="${r.a[k]>0}">
\[ ${r.a[k]} \cdot ${r.c[k]} \equiv ${r.b[k]} \pmod {${r.m[k]}} \quad c_${k+1}=${r.c[k]}\]
</c:if>
</c:forEach>
<BR>

<c:forEach begin="0" end="${r.l-1}" var="k">
<c:if test="${r.a[k]>0}">
\[ n_${k+1}=M/m_${k+1}=${r.n[k]} \]
\[ n_${k+1}\cdot\tilde n_${k+1} = ${r.n[k]}\cdot${r.i[k]} = ${r.n[k]*r.i[k]} \equiv1 \pmod {${r.m[k]}} \]
\[ \tilde n_${k+1}=${r.i[k]} \]
\[ \]
</c:if>
</c:forEach>

\[ x_0 = <c:forEach begin="0" end="${r.l-1}" var="k"> + c_${k+1} \cdot n_${k+1} \cdot \tilde n_${k+1} </c:forEach> \]
\[ x_0 = <c:forEach begin="0" end="${r.l-1}" var="k"> + ${r.c[k]} \cdot ${r.n[k]} \cdot ${r.i[k]} </c:forEach> = ${r.x0} \]
<BR>

\[ \text{Solution:} \]
\[ x \equiv ${r.y} \pmod {${r.product}} \]

</c:if>

</c:if>

