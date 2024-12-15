<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<script src="https://cdn.plot.ly/plotly-2.26.0.min.js" charset="utf-8"></script>

<form action="">
<div class="mform">
<div class="mtitle">Primes</div>
<div class="mbody">
		<c:forEach var="p" items="${primes}">
			<c:if test="${p>0}">
			    ${p}
				<!-- <a href="?waction=detail&p=${p}">${p}</a> -->
			</c:if>
		</c:forEach>
	<BR>	
	<button type="submit" value="reload" name="waction">reload</button>
</div>
</div>
</form>
<br clear="all" />

limit : ${intPrimes.limit}<BR>
count : ${intPrimes.count}<BR>
max   : ${intPrimes.last}<BR>


<div id='myDiv'></div>

<script type="text/javascript">
		var pi = {
		  x: [ <c:forEach var="p" items="${primePairs}">${p.a},</c:forEach> ],
		  y: [ <c:forEach var="p" items="${primePairs}">${p.b},</c:forEach> ],
		  name: 'Pi(x)'
		};

		var xlox = {
				  x: [ <c:forEach var="p" items="${xloxPairs}">${p.a},</c:forEach> ],
				  y: [ <c:forEach var="p" items="${xloxPairs}">${p.b},</c:forEach> ],
				  name: 'x/log(x)'
				};

		var data = [pi, xlox];

		Plotly.newPlot('myDiv', data);
		
</script>
