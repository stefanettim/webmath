<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<script type="text/javascript">
function myFunction() {
	  var x = document.getElementById("solution");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
</script>

<!-- 
<p>
  When \(a \ne 0\), there are two solutions to \(ax^2 + bx + c = 0\) and they are
  \[x = {-b \pm \sqrt{b^2-4ac} \over 2a}.\]
</p>  
 -->

<div class="mexample">
<div class="mtitle">Problem</div>
<div class="mbody">
\[ \text{Is this an integer?} \]
\[ \sqrt[3]{\sqrt{50}+7}-\sqrt[3]{\sqrt{50}-7} \]
</div>
</div>
<br clear="all" />

<BR>
<button onclick="myFunction()">Show/Hide solution</button> 
<BR>
<BR>

<div id="solution" style="display: none">
\[ \alpha=\sqrt[3]{\sqrt{50}+7} \]
\[ \beta =\sqrt[3]{\sqrt{50}-7} \]
\[ N=\alpha-\beta \]
\[ \]
\[ \text{approximate }\alpha:\]
\[ 49\lt50\lt64 \qquad \sqrt{49}\lt\sqrt{50}\lt\sqrt{64} \qquad 7\lt\sqrt{50}\lt8 \]
\[ 14\lt{\sqrt{50}+7}\lt15 \qquad 8\lt\sqrt{50}+7\lt27 \qquad 8\lt\alpha^3\lt27 \]
\[ 2\lt\alpha\lt3\]
\[ \alpha \approx 2.x \]
\[ \]
\[ \text{approximate }\beta:\]
\[ 1=50-49=({\sqrt{50}+7})({\sqrt{50}-7}) \]
\[ 1=\alpha^3\beta^3  \qquad \beta^3 = 1/{\alpha^3} \qquad 0\lt\beta=1/\alpha \]
\[ 0\lt\beta\lt1 \]
\[ \beta \approx 0.y \]
\[ \]
\[ \text{approximations:}\]
\[ N\approx2.x-0.y\]
\[ 1 \lt N \lt 3 \]
\[ \]
\[ \text{guess }N=2 :\]
\[ N=2,\qquad2=\alpha-\beta\qquad2+\beta=\alpha\qquad(2+\beta)^3=\alpha^3 :\]
\[ 8+12\beta+6\beta^2+\beta^3=\alpha^3\]
\[ \]
\[ \text{Substitute } \beta^3\text{ and }\alpha^3\text{, but not }\beta^2\text{ and }\beta :\]  
\[ 8+12\beta+6\beta^2+\sqrt{50}-7=\sqrt{50}+7 \]
\[ 6\beta^2+12\beta-6=0 \]
\[ \beta^2+2\beta-1=0 \]
\[ \beta_{1,2} = {-b \pm \sqrt{b^2-4ac} \over 2a} = {-b/2 \pm \sqrt{(b/2)^2-ac} \over a}=-1\pm\sqrt{2}\]
\[\beta\gt0\]
\[ \beta=-1+\sqrt{2}\]
\[ \]
\[ \text{let's check if this form of }\beta\text{ is the same as defined before:}\]
\[ -1+\sqrt{2}=\sqrt[3]{\sqrt{50}-7}\]  
\[ (-1+\sqrt{2})^3=\sqrt{50}-7\]
\[ -1+3\sqrt{2}-3\cdot2+2\sqrt{2}=5\sqrt{2}-7\]
\[ 5\sqrt{2}-7=5\sqrt{2}-7\]
\[ 0=0 \]
\[ \]
<div class="mexample">
<div class="mbody">
\[ N=2\]  
</div>
</div>
<br clear="all" />

</div>
