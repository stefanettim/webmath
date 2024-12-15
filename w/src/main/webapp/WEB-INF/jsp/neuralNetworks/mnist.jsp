<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<script>
if ( window.history.replaceState ) {
  window.history.replaceState( null, null, window.location.href );
}
</script>

<style>
#sig-canvas {
  border: 2px solid #CCCCCC;
  border-radius: 5px;
  background-color: #FFF;
  cursor: crosshair;
}
</style>
				 	
	<div class="container">
		<h1>MNIST NeuralNetwork Reader</h1>
	
	<c:if test="${not empty readResult}">
		<div class="row">
			<div class="col-md-12">
				<p id="readId">
					&nbsp;Read:${readResult}	
				</p>
			</div>
		</div>
	</c:if>
		<div class="row">
			<div class="col-md-12">
				<p>Draw a digit</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
		 		<canvas id="sig-canvas" width="280" height="280" style="touch-action: none">
		 			Get a better browser, bro.
		 		</canvas>
		 	</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<p></p>
				<button class="btn btn-primary" id="sig-submitBtn">Submit</button>
				<button class="btn btn-default" id="sig-clearBtn">Clear</button>
			</div>
		</div>
	</div>

<form method="post" accept-charset="utf-8" name="form1" action="mnist">
	<input type="hidden" name="waction" value="read" >
	<input type="hidden" name="hidden_data" id='hidden_data' />
</form>		 	
		 		
		 		
<script>
(function() {
	  window.requestAnimFrame = (function(callback) {
	    return window.requestAnimationFrame ||
	      window.webkitRequestAnimationFrame ||
	      window.mozRequestAnimationFrame ||
	      window.oRequestAnimationFrame ||
	      window.msRequestAnimaitonFrame ||
	      function(callback) {
	        window.setTimeout(callback, 0.01);
	      };
	  })();
	  
	  

	  var canvas = document.getElementById("sig-canvas");
	  var ctx = canvas.getContext("2d");

	  function initContext() {
		  ctx.strokeStyle = "#000";
		  ctx.lineWidth = 12;
	  }
	  
	  initContext();

	  
	  var drawing = false;
	  var mousePos = {
	    x: 0,
	    y: 0
	  };
	  var lastPos = mousePos;

	  canvas.addEventListener("mousedown", function(e) {
	    drawing = true;
	    lastPos = getMousePos(canvas, e);
	    
	    ctx.moveTo(lastPos.x, lastPos.y);
	    
	    document.getElementById('readId').innerHTML = '&nbsp;';
	    
	    $('html, body').css({
	        overflow: 'hidden',
	        height: '100%'
	    });
	    
	  }, false);

	  canvas.addEventListener("mouseup", function(e) {
	    drawing = false;
	    
	    $('html, body').css({
	        overflow: 'auto',
	        height: 'auto'
	    });
	    
	  }, false);

	  canvas.addEventListener("mousemove", function(e) {
	    mousePos = getMousePos(canvas, e);
	  }, false);

	  // Add touch event support for mobile
	  canvas.addEventListener("touchstart", function(e) {

	  }, false);

	  canvas.addEventListener("touchmove", function(e) {
	    var touch = e.touches[0];
	    var me = new MouseEvent("mousemove", {
	      clientX: touch.clientX,
	      clientY: touch.clientY
	    });
	    canvas.dispatchEvent(me);
	  }, false);

	  canvas.addEventListener("touchstart", function(e) {
	    mousePos = getTouchPos(canvas, e);
	    var touch = e.touches[0];
	    var me = new MouseEvent("mousedown", {
	      clientX: touch.clientX,
	      clientY: touch.clientY
	    });
	    canvas.dispatchEvent(me);
	  }, false);

	  canvas.addEventListener("touchend", function(e) {
	    var me = new MouseEvent("mouseup", {});
	    canvas.dispatchEvent(me);
	  }, false);

	  function getMousePos(canvasDom, mouseEvent) {
	    var rect = canvasDom.getBoundingClientRect();
	    return {
	      x: mouseEvent.clientX - rect.left,
	      y: mouseEvent.clientY - rect.top
	    }
	  }

	  function getTouchPos(canvasDom, touchEvent) {
	    var rect = canvasDom.getBoundingClientRect();
	    return {
	      x: touchEvent.touches[0].clientX - rect.left,
	      y: touchEvent.touches[0].clientY - rect.top
	    }
	  }

	  function renderCanvas() {
	    if (drawing) {
	      //ctx.moveTo(lastPos.x, lastPos.y);
	      ctx.lineTo(mousePos.x, mousePos.y);
	      ctx.stroke();
	      lastPos = mousePos;
	    }
	  }

	  // Prevent scrolling when touching the canvas
	  document.body.addEventListener("touchstart", function(e) {
	    if (e.target == canvas) {
	      e.preventDefault();
	    }
	  }, false);
	  document.body.addEventListener("touchend", function(e) {
	    if (e.target == canvas) {
	      e.preventDefault();
	    }
	  }, false);
	  document.body.addEventListener("touchmove", function(e) {
	    if (e.target == canvas) {
	      e.preventDefault();
	    }
	  }, false);

	  (function drawLoop() {
	    requestAnimFrame(drawLoop);
	    renderCanvas();
	  })();

	  function clearCanvas() {
		  
		  var context = canvas.getContext("2d");
		  context.clearRect(0, 0, canvas.width, canvas.height);
		  canvas.width=canvas.width;
		  initContext();

	  }

	  // Set up the UI
	  var clearBtn = document.getElementById("sig-clearBtn");
	  var submitBtn = document.getElementById("sig-submitBtn");

	  clearBtn.addEventListener("click", function(e) {
	    clearCanvas();
	  }, false);
	  
	  submitBtn.addEventListener("click", function(e) {
	    var dataURL = canvas.toDataURL();
		document.getElementById('hidden_data').value = dataURL;
		document.forms["form1"].submit();
	  }, false);

	})();
</script>		 		