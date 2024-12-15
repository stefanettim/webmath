<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>example</title>
  <link rel="stylesheet" href="${ctx}/css/zpoly.css" />

<script type="text/javascript">
function updateSlider(slideAmount) {
var display = document.getElementById("chosen");
gr=slideAmount/100*2;
display.innerHTML="r: "+gr;
draw()
}
</script>
  
</head>
<body>

<div id="main">

<div style="float:left;">

<canvas id="gCanvas" width="300" height="300">NoCanvas</canvas>

<div id="panel">

<div id="controls">
<div id="slider">0 <input id="slide" type="range" min="1" max="100" step="1" value="50" onmousemove="updateSlider(this.value)"/> 2</div>
<div id="chosen">r: 1</div>
</div>

</div>

<p id="fz">\[f(z) = {a*z^3+b*z^2+c*z+d}\]</p>
<p id="pa">\[a^2\]</p>
<p id="pb"></p>
<p id="pc"></p>
<p id="pd"></p>

</div>

<canvas id="fCanvas" width="600" height="600"></canvas>


</div>

<script>
// poly coefficients
var p0x=0.9
var p0y=0.9
var p1x=0.2
var p1y=0.7
var p2x=-0.1
var p2y=0.5
var p3x=-0.2
var p3y=0.8

var gCanvas = document.getElementById("gCanvas");
var gc = gCanvas.getContext("2d");
 
var fCanvas = document.getElementById("fCanvas");
var fc = fCanvas.getContext("2d");
var gr=1.0

var gSize=gCanvas.width;
var fSize=fCanvas.width;


function draw(){

var gxr1=-2.0
var gxr2=2.0
var gyr1=-2.0
var gyr2=2.0

var gsx=gxr2-gxr1
var gsy=gyr2-gyr1

   //console.log("gx:"+gx+" gy:" +gy);

var fxr1=-6.0
var fxr2=6.0
var fyr1=-6.0
var fyr2=6.0

var fsx=fxr2-fxr1
var fsy=fyr2-fyr1


gc.clearRect(0, 0, gCanvas.width, gCanvas.height);
gc.beginPath();

var y0=gSize-gSize*(-gyr1)/gsy;
gc.strokeStyle = '#bbb';
gc.moveTo(0, y0);
gc.lineTo(gSize, y0);
gc.strokeStyle = '#ddd';
for(var fxr=fxr1;fxr<fxr2;fxr++)
{
	gc.moveTo(fxr, fyr1);
	gc.lineTo(fxr, fyr2);
}

var x0=0+gSize*(-gxr1)/gsx;
gc.moveTo(x0, 0);
gc.lineTo(x0, gSize);

gc.strokeStyle = '#aaa';
gc.font = "20px Consolas";
gc.fillText("z", 30, 30);
gc.stroke();

fc.clearRect(0, 0, fCanvas.width, fCanvas.height);
fc.beginPath();
var fy0=fSize-fSize*(-fyr1)/fsy;
fc.moveTo(0, fy0);
fc.lineTo(fSize, fy0);
var fx0=0+fSize*(-fxr1)/fsx;
fc.moveTo(fx0, 0);
fc.lineTo(fx0, fSize);
fc.strokeStyle = '#aaa';
fc.font = "20px Consolas";
fc.fillText("f(z)", 30, 30);
fc.stroke();

fc.strokeStyle = '#000';


var gcInit = false;
var fcInit = false;



var steps=120;
var smallteta=2.0*Math.PI/steps
for(var i=0;i<steps+1;i++)
{
   var teta=i*smallteta
   var x=gr*Math.cos(teta)
   var y=gr*Math.sin(teta)
   var gr2=gr*gr
   var x2=gr2*Math.cos(2.0*teta)
   var y2=gr2*Math.sin(2.0*teta)
   var gr3=gr2*gr
   var x3=gr3*Math.cos(3.0*teta)
   var y3=gr3*Math.sin(3.0*teta)
   
   var gcx=0.0+gSize*(x-gxr1)/gsx
   var gcy=gSize-gSize*(y-gyr1)/gsy
   
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

   var fcx=0.0+fSize*(fx-fxr1)/fsx
   var fcy=fSize-fSize*(fy-fyr1)/fsy

   //console.log("teta:"+teta+" x:" +x+" y:" +y+" gx:"+gx+" gy:"+gy);
   if(!gcInit)
   {
	gc.beginPath();
	gc.moveTo(gcx, gcy);
	gcInit=true
   }
   else
   {
	gc.lineTo(gcx, gcy);
   }
   
   if(!fcInit)
   {
	fc.beginPath();
	fc.moveTo(fcx, fcy);
	fcInit=true
   }
   else
   {
	fc.lineTo(fcx, fcy);
   }
}

gc.strokeStyle = '#000';
gc.stroke();
fc.stroke();
}


draw();

const node = document.getElementById('pa');
MathJax.typesetClear();
MathJax.typesetClear([node]);
node.innerHTML = "\[ a^2 = "+p0x+"+i"+p0y+" \]";
document.getElementById("pa").innerHTML = "\[ a = "+p0x+"+i"+p0y+" \]";
document.getElementById("pb").innerHTML = "\[ b = "+p1x+"+i"+p1y+" \]";
document.getElementById("pc").innerHTML = "\[ c = "+p2x+"+i"+p2y+" \]";
document.getElementById("pd").innerHTML = "\[ d = "+p3x+"+i"+p3y+" \]";
MathJax.typesetPromise([node]).then(() => { });
MathJax.typesetPromise([node]);
MathJax.typeset();


</script>

</body>