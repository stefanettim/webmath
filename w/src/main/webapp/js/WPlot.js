class WPlot {

  constructor(canvasName, sizeX, sizeY) {
    this.canvasName = canvasName;
    this.sizeX = sizeX;
    this.sizeY = sizeY;

    this.xmin= -this.sizeX
    this.xmax=  this.sizeX
    this.ymin= -this.sizeY
    this.ymax=  this.sizeY

    this.sx = this.xmax - this.xmin
    this.sy = this.ymax - this.ymin
  }
  
  setColor(color){
	this.gc.strokeStyle = color;
  }

  setLineWidth(lineWidth) {
	this.gc.lineWidth = lineWidth;
  }

  cross(x, y, w, color){
 	  var lw = this.gc.lineWidth;
 	  this.gc.lineWidth = 2;
	  this.setColor(color);
	  this.line(x-w, y, x+w, y);
	  this.line(x, y-w, x, y+w);
	  this.gc.lineWidth=lw;
	}

  moveTo(x, y){
	if(this.lastX==null) this.lastX=x;
	if(this.lastY==null) this.lastY=y;
	this.gc.moveTo(x, y);
	}
	
  lineTo(x, y){
	if(this.lastX==null) this.lastX=x;
	if(this.lastY==null) this.lastY=y;
	
    this.line(this.lastX, this.lastY, x, y);
	
	this.lastX=x;
	this.lastY=y;
  }
  
  line(x1, y1, x2, y2){
	  
	var gcx1 = 0            + this.canvasX * ( x1 - this.xmin ) / this.sx;
	var gcy1 = this.canvasY - this.canvasY * ( y1 - this.ymin ) / this.sy;
	var gcx2 = 0            + this.canvasX * ( x2 - this.xmin ) / this.sx;
	var gcy2 = this.canvasY - this.canvasY * ( y2 - this.ymin ) / this.sy;
	  
	this.gc.beginPath();
	this.gc.moveTo(gcx1, gcy1);
	this.gc.lineTo(gcx2, gcy2);
	this.gc.stroke();

  }


  init(){
	this.panel = document.getElementById(this.canvasName+"Panel");
	this.canvas = document.getElementById(this.canvasName+"Canvas");
    this.gc = this.canvas.getContext("2d");

// get current size of the canvas
let rect = this.panel.getBoundingClientRect();
// increase the actual size of our canvas
this.canvas.width = (rect.width-4);
this.canvas.height = (rect.height-4);
// ensure all drawing operations are scaled
this.gc.scale(1, 1);
// scale everything down using CSS
this.canvas.style.width = (rect.width-4) + 'px';
this.canvas.style.height = (rect.height-4) + 'px';

    this.canvasX = (rect.width-4);
    this.canvasY = (rect.height-4);

    this.gc.fillStyle = "#EEE";
    //this.gc.fillStyle = "#000";
	this.gc.fillRect(0, 0, this.canvas.width, this.canvas.height);

    this.setColor("#ddd");
	this.gc.lineWidth = 1;
	//this.gc.setLineDash([1, 2]);

	for(var x=this.xmin; x<this.xmax;x++)
	  this.line(x,this.ymin,x,this.ymax);
	
	for(var y=this.ymin; y<this.ymax;y++)
	  this.line(this.xmin,y,this.xmax,y);

	this.gc.lineWidth = 2;
	this.gc.setLineDash([]);
    this.setColor("#bbb");
	this.line(this.xmin,0,this.xmax,0);
	this.line(0,this.ymin,0,this.ymax);

    this.setColor("#F22");
	this.gc.lineWidth = 1;
  }
  
  title(title){
    this.gc.fillStyle = "#000";
	this.gc.font = "20px Consolas";
	this.gc.fillText(title, 30, 30);
  }

}


