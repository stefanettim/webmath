<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>ZPoly</title>
  <link rel="stylesheet" href="${ctx}/css/zpoly.css" />
  <script src="${ctx}/js/WPlot.js"></script>

<script type="text/javascript">
function updateSlider(slideAmount) {
var display = document.getElementById("chosen");
gr=slideAmount/100*2;
display.innerHTML="r: "+(gr).toFixed(2);
draw()
}
</script>
 
<script>
MathJax = {
  tex: {
    inlineMath: [['$', '$'], ['\\(', '\\)']]
  },
  svg: {
    fontCache: 'global'
  }
};
</script>
<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-chtml.js" id="MathJax-script"></script>
  
</head>
<body>

<div id="main">

<div style="float:left;">

<canvas id="gCanvas" width="300" height="300">NoCanvas</canvas>

<div id="panel">

<div id="controls">
<div id="slider">0 <input id="slide" type="range" min="1" max="100" step="1" value="50" onmousemove="updateSlider(this.value)"/> 2</div>
<div id="chosen">r: </div>
</div>
<BR>
<div id="fz">$f(z) = {a \cdot z^3 + b \cdot z^2 + c \cdot z + d}$</div>
<div id="pa"></div>
<div id="pb"></div>
<div id="pc"></div>
<div id="pd"></div>

</div>

</div>

<canvas id="fCanvas" width="600" height="600"></canvas>


</div>

<script>
// poly coefficients
var p0x=-1
var p0y=-1
var p1x=0.2
var p1y=0.7
var p2x=-0.1
var p2y=0.5
var p3x=1
var p3y=1

var plotG = new WPlot("gCanvas",2,2);
var plotF = new WPlot("fCanvas",8,8);

function draw(){

	plotG.init();
	plotG.title("z");

	plotF.init();
	plotF.title("f(z)");
	plotF.setColor("#33F");

var steps=100;
var smallteta=2.0*Math.PI/steps
var gr2=gr*gr
var gr3=gr2*gr
for(var i=0;i<steps+1;i++)
{
   var teta=i*smallteta
   var x=gr*Math.cos(teta)
   var y=gr*Math.sin(teta)
   var x2=gr2*Math.cos(2.0*teta)
   var y2=gr2*Math.sin(2.0*teta)
   var x3=gr3*Math.cos(3.0*teta)
   var y3=gr3*Math.sin(3.0*teta)
   
   var fx0 = p0x
   var fy0 = p0y
   var fx1 = p1x*x-p1y*y;
   var fy1 = p1y*x+p1x*y;
   var fx2 = p2x*x2-p2y*y2;
   var fy2 = p2y*x2+p2x*y2;
   var fx3 = p3x*x3-p3y*y3;
   var fy3 = p3y*x3+p3x*y3;
   
   var fx = fx0+fx1+fx2+fx3;
   var fy = fy0+fy1+fy2+fy3;

	plotG.lineTo(x, y);
	plotF.lineTo(fx, fy);
}

}

var gr=1
updateSlider(50) 

  const sa = document.createElement('span')
  sa.textContent = "\\( a = "+p3x+"+i("+p3y+") \\)"
  const pa = document.querySelector('#pa')
  pa.appendChild(sa.cloneNode(true))
  
  const sb = document.createElement('span')
  sb.textContent = "\\( b = "+p2x+"+i("+p2y+") \\)"
  const pb = document.querySelector('#pb')
  pb.appendChild(sb.cloneNode(true))

  const sc = document.createElement('span')
  sc.textContent = "\\( c = "+p2x+"+i("+p2y+") \\)"
  const pc = document.querySelector('#pc')
  pc.appendChild(sc.cloneNode(true))

  const sd = document.createElement('span')
  sd.textContent = "\\( d = "+p0x+"+i("+p0y+") \\)"
  const pd = document.querySelector('#pd')
  pd.appendChild(sd.cloneNode(true))

  MathJax.typeset()
	
</script>

</body>