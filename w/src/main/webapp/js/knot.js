class t_linea { 
	
	constructor(){
		   this.x  = 0;    // punto
		   this.y  = 0;
		   this.z  = 0;
		   this.nx = 0;   // normale
		   this.ny = 0;
		   this.nz = 0;
		   this.bx = 0;   // binormale
		   this.by = 0;
		   this.bz = 0;
   } 
};

class  t_rete  { 

	constructor(){
		this.x;
		this.y;
		this.dis;
		this.red;
		this.green;
		this.blue; 
	}
	
};
	
class Nodo {
	
	constructor(canvasName){
	
	this.canvasName = canvasName;
	this.canvas = document.getElementById(this.canvasName);
    this.dc = this.canvas.getContext("2d");
		
this.lx=120;  // suddivisioni linea
this.ly=12;   // suddivisioni circonferenze
this.pi=3.141592653;
this.rg=150;
this.rp=23;  // raggio tubo
this.ox=300;
this.oy=300;
this.oz=300; // osservatore
this.vlrx=0;
this.vlry=0;
this.vlrz=-1;    // vettore luce rosso
this.vlgx=-1
this.vlgy=0;
this.vlgz=0;    // vettore luce verde
this.vlbx=0;
this.vlby=-1
this.vlbz=0;    // vettore luce blu
this.alfa=30;              // assonometria
this.beta=30;
this.cosa=Math.cos(this.alfa*this.pi/180);
this.cosb=Math.cos(this.beta*this.pi/180);
this.sena=Math.sin(this.alfa*this.pi/180);
this.senb=Math.sin(this.beta*this.pi/180);
this.fmin=0;
this.tpas=2*this.pi;
this.fpas=2*this.pi;
this.mx=240;   // coordinate schermo dell' origine
this.my=240;

var a,b,c,rosso,verde,blu;
var tx,ty,tz,xn,yn,xb,yb,dt,dn,db,xr,yr;
var lbx,lby,lbz,dlb;
var lrx,lry,lrz,dlr;
var lgx,lgy,lgz,dlg;
var x,y,z,s;
var fi;


		this.linea = [];
		 //new t_linea[this.lx+2];

		this.rete = [];
		
		for(var a=0;a<this.lx+2;a++)
		{
		  var ya = [];	
		  for(var b=0;b<this.ly+2;b++)
		  {
			ya[b]=new t_rete();
		  }
		  this.rete[a]=ya;
		}
		
		for(var a=0;a<this.lx+2;a++) 
			this.linea[a]=new t_linea();
    }
	
	hello(){
		document.write("Hello");
	}

calc_nodo(fi)    // equazione linea
{ this.x=this.rg*(0.24+Math.sin(this.fi-2*this.pi/3))*Math.cos(this.fi-2*this.pi/3)+this.rg/5*Math.sin(3*this.fi+this.pi/3);
  this.y=this.rg*(0.24+Math.sin(this.fi+2*this.pi/3))*Math.cos(this.fi+2*this.pi/3)+this.rg/5*Math.sin(3*this.fi+this.pi/3);
  this.z=this.rg*(0.24+Math.sin(this.fi            ))*Math.cos(this.fi            )+this.rg/5*Math.sin(3*this.fi+this.pi/3); 
  }
  
disegna_poligono(a,b)
{
dc.setColor(new Color(0,0,150));
quadx=[];
quady=[];
quadx[0]=rete[a][b].x;
quady[0]=rete[a][b].y;
quadx[1]=rete[a+1][b].x;
quady[1]=rete[a+1][b].y;
quadx[2]=rete[a+1][b+1].x;
quady[2]=rete[a+1][b+1].y;
quadx[3]=rete[a][b+1].x;
quady[3]=rete[a][b+1].y;
rosso=rete[a][b].red;
verde=rete[a][b].green;
blu=rete[a][b].blue;
dc.setColor(new Color(rosso,verde,blu));
dc.fillPolygon(quadx,quady,4);
}
  
  
disegna_nodo()
{   var xs,ys,zs; // punto linea successivo
    var xp,yp,zp; // punto linea precedente
    var px = this.lx;
    var dfi=0.000001;
	
this.dc.strokeStyle = "#0F0";

// calcola linea x,y,z
for(var a=0;a<=this.lx+1;a++)
  {	 this.fi=(this.fpas*a)/this.px-this.fmin;
	 this.calc_nodo(this.fi);
	 this.linea[a].x=this.x;
	 this.linea[a].y=this.y;
	 this.linea[a].z=this.z;
	 this.calc_nodo(this.fi-this.dfi); this.xp=this.x; this.yp=this.y; this.zp=this.z;
	 this.calc_nodo(this.fi+this.dfi); this.xs=this.x; this.ys=this.y; this.zs=this.z;
	// calcola tangente (non memorizzata nell' array)
	this.tx=this.xs-this.linea[a].x;
	this.ty=this.ys-this.linea[a].y;
	this.tz=this.zs-this.linea[a].z;
	this.dt=Math.sqrt(this.tx*this.tx+this.ty*this.ty+this.tz*this.tz)+0.0000000000001; // normalizza
	this.tx=this.tx/this.dt;   this.ty=this.ty/this.dt;    this.tz=this.tz/this.dt;

	// calcola normale
	this.linea[a].nx=this.xs-2*this.linea[a].x+this.xp; // derivata seconda
	this.linea[a].ny=this.ys-2*this.linea[a].y+this.yp;
	this.linea[a].nz=this.zs-2*this.linea[a].z+this.zp;
	this.dn=Math.sqrt(this.linea[a].nx*this.linea[a].nx
		  +this.linea[a].ny*this.linea[a].ny+this.linea[a].nz*this.linea[a].nz)+0.00000000000001;
	this.linea[a].nx/=this.dn;   this.linea[a].ny/=this.dn;    this.linea[a].nz/=this.dn;

	// calcola binormale
	this.linea[a].bx=this.ty*this.linea[a].nz-this.tz*this.linea[a].ny; // prodotto vettore t^n
	this.linea[a].by=this.tz*this.linea[a].nx-this.tx*this.linea[a].nz;
	this.linea[a].bz=this.tx*this.linea[a].ny-this.ty*this.linea[a].nx;
	this.db=Math.sqrt(this.linea[a].bx*this.linea[a].bx
		  +this.linea[a].by*this.linea[a].by+this.linea[a].bz*this.linea[a].bz)+0.000000000001;
	this.linea[a].bx/=this.db;   this.linea[a].by/=this.db;    this.linea[a].bz/=this.db;

	}
	
// vettore luce normalizzato rosso
var lrx=this.vlrx; var lry=this.vlry; var lrz=this.vlrz;
var dlr=Math.sqrt((lrx*lrx)+(lry*lry)+(lrz*lrz))+0.000000000000001;
var lrx=lrx/dlr; var lry=lry/dlr; var lrz=lrz/dlr;
// vettore luce normalizzato verde
var lgx=this.vlgx; var lgy=this.vlgy; var lgz=this.vlgz;
var dlg=Math.sqrt((lgx*lgx)+(lgy*lgy)+(lgz*lgz))+0.000000000000001;
var lgx=lgx/dlg; var lgy=lgy/dlg; var lgz=lgz/dlg;
// vettore luce normalizzato blu
var lbx=this.vlbx; var lby=this.vlby; var lbz=this.vlbz;
var dlb=Math.sqrt((lbx*lbx)+(lby*lby)+(lbz*lbz))+0.000000000000001;
var lbx=lbx/dlb; var lby=lby/dlb; var lbz=lbz/dlb;


// calcola rete di poligoni,distanza dall' osservatore,inclinazione luce.
for(var a=1;a<=this.lx;a++)
 for(var b=0;b<=this.ly+1;b++)
	{ this.fi=2*this.pi/this.ly*b;
	this.rete[a][b].x=  
	       this.linea[a].x*this.cosb-this.linea[a].y*this.cosa+this.mx
		 +(this.linea[a].bx*this.cosb-this.linea[a].by*this.cosa)*Math.sin(this.fi)*this.rp
		 +(this.linea[a].nx*this.cosb-this.linea[a].ny*this.cosa)*Math.cos(this.fi)*this.rp;
	this.rete[a][b].y=
	       this.linea[a].x*this.senb +this.linea[a].y*this.sena-this.linea[a].z+this.my
		 +(this.linea[a].bx*this.senb+this.linea[a].by*this.sena-this.linea[a].bz)*Math.sin(this.fi)*this.rp
		 +(this.linea[a].nx*this.senb+this.linea[a].ny*this.sena-this.linea[a].nz)*Math.cos(this.fi)*this.rp;
		 
	this.x=this.linea[a].x+(this.linea[a].nx*Math.cos(this.fi)+this.linea[a].bx*Math.sin(this.fi))*this.rp;
	this.y=this.linea[a].y+(this.linea[a].ny*Math.cos(this.fi)+this.linea[a].by*Math.sin(this.fi))*this.rp;
	this.z=this.linea[a].z+(this.linea[a].nz*Math.cos(this.fi)+this.linea[a].bz*Math.sin(this.fi))*this.rp;
	
	this.rete[a][b].dis=Math.sqrt((this.ox-this.x)*(this.ox-this.x)+(this.oy-this.y)*(this.oy-this.y)+(this.oz-this.z)*(this.oz-this.z));

	this.x=this.linea[a].nx*Math.cos(this.fi)+this.linea[a].bx*Math.sin(this.fi);
	this.y=this.linea[a].ny*Math.cos(this.fi)+this.linea[a].by*Math.sin(this.fi);
	this.z=this.linea[a].nz*Math.cos(this.fi)+this.linea[a].bz*Math.sin(this.fi);

	this.rete[a][b].red  = 255*Math.acos(this.x*this.lrx+this.y*this.lry+this.z*this.lrz)/this.pi;
	this.rete[a][b].green= 255*Math.acos(this.x*this.lgx+this.y*this.lgy+this.z*this.lgz)/this.pi;
	this.rete[a][b].blue = 255*Math.acos(this.x*this.lbx+this.y*this.lby+this.z*this.lbz)/this.pi;

	} // doppio for

 for(var b=0;b<=this.ly+1;b++)
{ this.rete[this.lx+1][b].x  =this.rete[1][b].x;
  this.rete[this.lx+1][b].y  =this.rete[1][b].y;
  this.rete[this.lx+1][b].dis=this.rete[1][b].dis;
  this.rete[this.lx+1][b].red  =this.rete[1][b].red;
  this.rete[this.lx+1][b].green=this.rete[1][b].green;
  this.rete[this.lx+1][b].blue =this.rete[1][b].blue;
}

 // disegna poligoni a partire dal piu' lontano
 var lontano;
 var m=0,n=0;
 var tot=(this.lx+1)*(this.ly+1);
 for(var c=1;c<=tot;c++)
  { lontano=-1;
		 for(a=1;a<=this.lx;a++)
		 for(b=0;b<this.ly;b++)
		 if(this.rete[a][b].dis>=lontano)
		 	{ m=a;
			  n=b;
			  lontano=this.rete[a][b].dis; }
	 this.rete[m][n].dis=-1;
	 if(lontano!=-1) this.disegna_poligono(m,n);
	 //System.out.println(c+"/"+tot);
  } // for c
}

}		   
		   


