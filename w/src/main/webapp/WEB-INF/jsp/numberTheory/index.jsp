<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<H1>Number Theory</H1>
<div class="button-container">
<a href="${ctx}/a/numberTheory/euclideanAlgorithm" class="button">Euclidean algorithm</a>
<a href="${ctx}/a/numberTheory/mixedResults" class="button">mixed results</a>
<a href="${ctx}/a/numberTheory/linearCongruence" class="button">linear congruence</a>
<a href="${ctx}/a/numberTheory/eulerTheorem" class="button">Euler's theorem</a>
<a href="${ctx}/a/numberTheory/wilsonTheorem" class="button">Wilson's theorem</a>
<a href="${ctx}/a/numberTheory/primes" class="button">primes</a>
<a href="${ctx}/a/numberTheory/fermatNumbers" class="button">Fermat's numbers</a>
<a href="${ctx}/a/numberTheory/chineseReminderTheorem" class="button">Chinese Reminder Theorem</a>
<a href="${ctx}/a/numberTheory/polynomialCongruence" class="button">polynomial congruence</a>
<a href="${ctx}/a/numberTheory/arithmeticFunctions" class="button">arithmetic functions</a>
<a href="${ctx}/a/numberTheory/powers" class="button">powers</a>
<a href="${ctx}/a/numberTheory/primitiveRoots" class="button">primitive roots</a>
</div>
