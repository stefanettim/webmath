<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:formatDate value="${requestScope.startTime}" pattern="yyyyMMdd" var="wdate"/>
<c:set var="zplotver" value="20231004" />


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>zPoly</title>

<script src="${ctx}/js/MathJax.js?date=${wdate}"></script>
<script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
<script id="MathJax-script" async=true src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>

<link rel="stylesheet" href="${ctx}/css/math.css?zplotver=${zplotver}" />
<script src="${ctx}/js/WPlot.js?zplotver=${zplotver}"></script>

<script type="text/javascript">
	function updateRadiusSlider(slideAmount) {
		var display = document.getElementById('radius');
		gr = slideAmount / 100 * 2;
		display.innerHTML = (gr).toFixed(2);
		draw()
	}
	function updateAngleSlider(slideAmount) {
		var display = document.getElementById('angle');
		teta = slideAmount / 100 * 6.28318530718;
		display.innerHTML = (teta).toFixed(2);
		draw()
	}
</script>

<STYLE>
mjx-container[jax="CHTML"] {
  font-size: medium !important; 
  display: inline-grid;
  overflow-x: hidden;
  overflow-y: hidden;
  max-width: 100%;
  padding: 0;
  margin: 0.4em 0 0 0;
  line-height: 1.3em;
}
</STYLE>

</head>

<body>
<div id="main">
	<div id="maintitle">
	<h1>zPoly</h1>
	</div>
	<br clear="all" />
	
	<div id="mainleft">
			<div id="ctrlpanel">
			<div id="ctrlpanelInside">
						$z \rightarrow f(z)$<BR>
					    $f(z) = { z^4 + a z^3 + b z^2 + c z + d }$<BR>
						$ z = r \cdot e^{i \theta} = $ <span id="zx">0.00</span>$+i($<span id="zy">0.00</span>$)$<BR>
						<div id="pa"></div>
						<div id="pb"></div>
						<div id="pc"></div>
						<div id="pd"></div>
						$ f(z)=$ <span id="fzx">0.00</span>$ + i ($<span id="fzy">0.00</span>$)$
							<div id="slider">
								$0\;$<input id="slideRadius" type="range" min="1" max="100" step="1"
									value="50" />$\;2$<BR>
									$\quad r=$ <span id="radius">1.00</span>
							</div>		
							<div id="slider">
								$0\;$<input id="slideAngle" type="range" min="0" max="100" step="1"
									value="0" />$\;2\pi$<BR>
									$\quad \theta=$ <span id="angle">0.00</span>
							</div>
			</div>				
			<br clear="all" />
			</div>
	  		<div id="gPanel">
				<canvas id="gCanvas"></canvas>
			</div>	
			<br clear="all" />
	</div>
	<div id="mainright">
		<div id="fPanel">
			<canvas id="fCanvas"></canvas>
	  	</div>
	</div>
	<br clear="all" />

</div>	
<br clear="all" />



<script>

const inputRadius = document.querySelector("#slideRadius");
inputRadius.addEventListener("input", (event) => {
	updateRadiusSlider(event.target.value);
});

const inputAngle = document.querySelector("#slideAngle");
inputAngle.addEventListener("input", (event) => {
	updateAngleSlider(event.target.value);
});

function reportWindowSize() {
  draw()
}
window.onresize = reportWindowSize;


		// poly coefficients
		var p0x = -1
		var p0y = -1
		var p1x = 0.3
		var p1y = 0.7
		var p2x = -0.3
		var p2y = 0.5
		var p3x = -0.2
		var p3y = 0.6
		var p4x = 1
		var p4y = 1

		var plotG = new WPlot("g", 2, 2);
		var plotF = new WPlot("f", 10, 10);

		var x;
		var y;
		var fx;
		var fy;

		function poly(r, teta) {

			x = r * Math.cos(teta)
			y = r * Math.sin(teta)

			var x2 = r * r * Math.cos(2.0 * teta)
			var y2 = r * r * Math.sin(2.0 * teta)
			var x3 = r * r * r * Math.cos(3.0 * teta)
			var y3 = r * r * r * Math.sin(3.0 * teta)
			var x4 = r * r * r * r * Math.cos(4.0 * teta)
			var y4 = r * r * r * r * Math.sin(4.0 * teta)

			var fx0 = p0x
			var fy0 = p0y
			var fx1 = p1x * x - p1y * y;
			var fy1 = p1y * x + p1x * y;
			var fx2 = p2x * x2 - p2y * y2;
			var fy2 = p2y * x2 + p2x * y2;
			var fx3 = p3x * x3 - p3y * y3;
			var fy3 = p3y * x3 + p3x * y3;
			var fx4 = p4x * x4 - p4y * y4;
			var fy4 = p4y * x4 + p4x * y4;

			fx = fx0 + fx1 + fx2 + fx3 + fx4;
			fy = fy0 + fy1 + fy2 + fy3 + fy4;

		}

		function draw() {


			plotG.init();
			plotG.title("z");
			//plotG.setColor("#006400"); // green
			plotG.setColor("#03F");
			plotG.setLineWidth(1);

			plotF.init();
			plotF.title("f(z)");
			plotF.setColor("#03F");
			plotF.setLineWidth(1);

			var steps = 200;
			var smallteta = 2.0 * Math.PI / steps

			for (var i = 0; i < steps + 1; i++) {
				var cteta = i * smallteta
				poly(gr, cteta)
				
				if(i==0)
					plotG.moveTo(x, y);
				
				plotG.lineTo(x, y);
				
				if(i==0)
					plotF.moveTo(fx, fy);
				
				plotF.lineTo(fx, fy);
			}

			poly(gr, teta)
			
			var display = document.getElementById('zx');
			display.innerHTML = (x).toFixed(2);

			var display = document.getElementById('zy');
			display.innerHTML = (y).toFixed(2);

			plotG.setColor("#F00");
			plotG.setLineWidth(1);
			plotG.line(0, 0, x, y);
			plotG.cross(x,y,0.08,"navy")

			var display = document.getElementById('fzx');
			display.innerHTML = (fx).toFixed(2);

			var display = document.getElementById('fzy');
			display.innerHTML = (fy).toFixed(2);

			plotF.setColor("#F00");
			plotF.setLineWidth(1);
			plotF.line(p0x, p0y, fx, fy);
			plotF.cross(fx,fy,0.2,"navy")

			/*
			plotF.setColor("#0F0");
			for (var i = 0; i < steps + 1; i++) {
				var teta = i * smallteta
				x = p0x + 1.5 * gr * Math.cos(teta)
				y = p0y + 1.5 * gr * Math.sin(teta)

				if (i == 0) {
					plotF.moveTo(x, y);
				} else {
					plotF.lineTo(x, y);
				}
			}
			*/
			

		}

		var gr = 1
		var teta = 0
		updateRadiusSlider(50)
		updateAngleSlider(0)

		const sa = document.createElement('span')
		sa.textContent = "\\( a = " + p3x + "+i(" + p3y + ") \\)"
		const pa = document.querySelector('#pa')
		pa.appendChild(sa.cloneNode(true))

		const sb = document.createElement('span')
		sb.textContent = "\\( b = " + p2x + "+i(" + p2y + ") \\)"
		const pb = document.querySelector('#pb')
		pb.appendChild(sb.cloneNode(true))

		const sc = document.createElement('span')
		sc.textContent = "\\( c = " + p2x + "+i(" + p2y + ") \\)"
		const pc = document.querySelector('#pc')
		pc.appendChild(sc.cloneNode(true))

		const sd = document.createElement('span')
		sd.textContent = "\\( d = " + p0x + "+i(" + p0y + ") \\)"
		const pd = document.querySelector('#pd')
		pd.appendChild(sd.cloneNode(true))

	</script>

</body>