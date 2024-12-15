<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<style>
img.resultImg {
background-color: #FFF;
  border: 2px solid #AAA;
  border-radius: 3px;
}
table.resultTable{
background-color: #EEF;
}
table.resultTable td, table.resultTable th {
  border: 1px solid #AAA;
  padding: 0px;
  text-align: center;
  font-weight: bold;
  font-size: small;
}
table.resultTable td {
align-content: center;
text-align: center;
}
</style>
		 	
		 	
<div id="result">
<h2 id="readId"><img class="resultImg"  src="${readResult.mnistImage}"/> Read Result: ${readResult.result}</h2>

<form name="form1" action="mnist">
	<button>Retry</button>
</form>		 	
</div>		 	

<BR><BR>
<table class="resultTable">

<tr>
<td>
Output Layer
<table>
<tr>
<Th>0</TH>
<Th>1</TH>
<Th>2</TH>
<Th>3</TH>
<Th>4</TH>
<Th>5</TH>
<Th>6</TH>
<Th>7</TH>
<Th>8</TH>
<Th>9</TH>
</tr>
<tr>
<c:forEach items="${readResult.activations}" var="activation">
<TD style="background-color: hsl(${(200 + (160 * activation))},100%,50%);"> <fmt:formatNumber type = "number" pattern = "0.00" value = "${activation}" /></TD>
</c:forEach>
</tr>
</table>

</td>
</tr>

<tr>
<td>Original Image<BR>
<img class="resultImg" src="${readResult.originalImage}"/></td>
</tr>
<tr>
<td>Converted Image<BR>
<img class="resultImg" src="${readResult.convertedImage}"/></td>
</tr>
<tr>
<td>Sub Image<BR>
<img class="resultImg"  src="${readResult.subImage}"/></td>
</tr>
<tr>
<td>
Square Image<BR>
<img class="resultImg"  src="${readResult.squareImage}"/></td>
</tr>
<tr>
<td>
Small Image<BR>
<img class="resultImg"  src="${readResult.smallImage}"/></td>
</tr>
<tr>
<td>
Center of Mass<BR>
<img class="resultImg"  src="${readResult.massCenterImage}"/></td>
</tr>
<tr>
<td>MNIST Image<BR>
<img class="resultImg"  src="${readResult.mnistImage}"/></td>
</tr>

</table>
