package math;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class c_nodo extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1077841824718615550L;
	Image offIm;
	Graphics offGr;

	int lx = 120; // suddivisioni linea
	int ly = 12; // suddivisioni circonferenze

	double pi = 3.141592653;
	double rg = 150;
	double rp = 23; // raggio tubo
	double ox = 300, oy = 300, oz = 300; // osservatore
	double vlrx = 0, vlry = 0, vlrz = -1; // vettore luce rosso
	double vlgx = -1, vlgy = 0, vlgz = 0; // vettore luce verde
	double vlbx = 0, vlby = -1, vlbz = 0; // vettore luce blu
	double alfa = 30; // assonometria
	double beta = 30;
	double cosa = Math.cos(alfa * pi / 180);
	double cosb = Math.cos(beta * pi / 180);
	double sena = Math.sin(alfa * pi / 180);
	double senb = Math.sin(beta * pi / 180);
	double fmin = 0;
	double tpas = 2 * pi, fpas = 2 * pi;
	int mx = 240; // coordinate schermo dell' origine
	int my = 240;

	class t_rete {
		int x;
		int y;
		double dis;
		int red;
		int green;
		int blue;
	};

	t_rete[][] rete = new t_rete[lx + 2][ly + 2];

	class t_linea {
		double x; // punto
		double y;
		double z;
		double nx; // normale
		double ny;
		double nz;
		double bx; // binormale
		double by;
		double bz;
	};

	t_linea[] linea = new t_linea[lx + 2];

	int a, b, c, rosso, verde, blu;
	double tx, ty, tz, xn, yn, xb, yb, dt, dn, db, xr, yr;
	double lbx, lby, lbz, dlb;
	double lrx, lry, lrz, dlr;
	double lgx, lgy, lgz, dlg;
	double x, y, z, s;
	double fi;

	public void calc_nodo(double fi) // equazione linea
	{
		x = rg * (0.24 + Math.sin(fi - 2 * pi / 3)) * Math.cos(fi - 2 * pi / 3) + rg / 5 * Math.sin(3 * fi + pi / 3);
		y = rg * (0.24 + Math.sin(fi + 2 * pi / 3)) * Math.cos(fi + 2 * pi / 3) + rg / 5 * Math.sin(3 * fi + pi / 3);
		z = rg * (0.24 + Math.sin(fi)) * Math.cos(fi) + rg / 5 * Math.sin(3 * fi + pi / 3);
	}

	public void disegna_poligono(Graphics dc, int a, int b) {
		dc.setColor(new Color(0, 0, 150));
		int[] quadx = new int[4];
		int[] quady = new int[4];
		quadx[0] = rete[a][b].x;
		quady[0] = rete[a][b].y;
		quadx[1] = rete[a + 1][b].x;
		quady[1] = rete[a + 1][b].y;
		quadx[2] = rete[a + 1][b + 1].x;
		quady[2] = rete[a + 1][b + 1].y;
		quadx[3] = rete[a][b + 1].x;
		quady[3] = rete[a][b + 1].y;
		rosso = rete[a][b].red;
		verde = rete[a][b].green;
		blu = rete[a][b].blue;
		dc.setColor(new Color(rosso, verde, blu));
		dc.fillPolygon(quadx, quady, 4);
	}

	public void disegna_nodo(Graphics dc) {
		double xs, ys, zs; // punto linea successivo
		double xp, yp, zp; // punto linea precedente
		double px = lx;
		double dfi = 0.000001;
		dc.setColor(new Color(0, 255, 0));
// calcola linea x,y,z
		for (a = 0; a <= lx + 1; a++) {
			fi = (fpas * a) / px - fmin;
			calc_nodo(fi);
			linea[a].x = x;
			linea[a].y = y;
			linea[a].z = z;
			calc_nodo(fi - dfi);
			xp = x;
			yp = y;
			zp = z;
			calc_nodo(fi + dfi);
			xs = x;
			ys = y;
			zs = z;
			// calcola tangente (non memorizzata nell' array)
			tx = xs - linea[a].x;
			ty = ys - linea[a].y;
			tz = zs - linea[a].z;
			dt = Math.sqrt(tx * tx + ty * ty + tz * tz) + 0.0000000000001; // normalizza
			tx /= dt;
			ty /= dt;
			tz /= dt;

			// calcola normale
			linea[a].nx = xs - 2 * linea[a].x + xp; // derivata seconda
			linea[a].ny = ys - 2 * linea[a].y + yp;
			linea[a].nz = zs - 2 * linea[a].z + zp;
			dn = Math.sqrt(linea[a].nx * linea[a].nx + linea[a].ny * linea[a].ny + linea[a].nz * linea[a].nz)
					+ 0.00000000000001;
			linea[a].nx /= dn;
			linea[a].ny /= dn;
			linea[a].nz /= dn;

			// calcola binormale
			linea[a].bx = ty * linea[a].nz - tz * linea[a].ny; // prodotto vettore t^n
			linea[a].by = tz * linea[a].nx - tx * linea[a].nz;
			linea[a].bz = tx * linea[a].ny - ty * linea[a].nx;
			db = Math.sqrt(linea[a].bx * linea[a].bx + linea[a].by * linea[a].by + linea[a].bz * linea[a].bz)
					+ 0.000000000001;
			linea[a].bx /= db;
			linea[a].by /= db;
			linea[a].bz /= db;

		}

// vettore luce normalizzato rosso
		lrx = vlrx;
		lry = vlry;
		lrz = vlrz;
		dlr = Math.sqrt((lrx * lrx) + (lry * lry) + (lrz * lrz)) + 0.000000000000001;
		lrx /= dlr;
		lry /= dlr;
		lrz /= dlr;
// vettore luce normalizzato verde
		lgx = vlgx;
		lgy = vlgy;
		lgz = vlgz;
		dlg = Math.sqrt((lgx * lgx) + (lgy * lgy) + (lgz * lgz)) + 0.000000000000001;
		lgx /= dlg;
		lgy /= dlg;
		lgz /= dlg;
// vettore luce normalizzato blu
		lbx = vlbx;
		lby = vlby;
		lbz = vlbz;
		dlb = Math.sqrt((lbx * lbx) + (lby * lby) + (lbz * lbz)) + 0.000000000000001;
		lbx /= dlb;
		lby /= dlb;
		lbz /= dlb;

// calcola rete di poligoni,distanza dall' osservatore,inclinazione luce.
		for (a = 1; a <= lx; a++)
			for (b = 0; b <= ly + 1; b++) {
				fi = 2 * pi / ly * b;
				rete[a][b].x = (int) (linea[a].x * cosb - linea[a].y * cosa + mx
						+ (linea[a].bx * cosb - linea[a].by * cosa) * Math.sin(fi) * rp
						+ (linea[a].nx * cosb - linea[a].ny * cosa) * Math.cos(fi) * rp);
				rete[a][b].y = (int) (linea[a].x * senb + linea[a].y * sena - linea[a].z + my
						+ (linea[a].bx * senb + linea[a].by * sena - linea[a].bz) * Math.sin(fi) * rp
						+ (linea[a].nx * senb + linea[a].ny * sena - linea[a].nz) * Math.cos(fi) * rp);
				x = linea[a].x + (linea[a].nx * Math.cos(fi) + linea[a].bx * Math.sin(fi)) * rp;
				y = linea[a].y + (linea[a].ny * Math.cos(fi) + linea[a].by * Math.sin(fi)) * rp;
				z = linea[a].z + (linea[a].nz * Math.cos(fi) + linea[a].bz * Math.sin(fi)) * rp;
				rete[a][b].dis = Math.sqrt((ox - x) * (ox - x) + (oy - y) * (oy - y) + (oz - z) * (oz - z));

				x = linea[a].nx * Math.cos(fi) + linea[a].bx * Math.sin(fi);
				y = linea[a].ny * Math.cos(fi) + linea[a].by * Math.sin(fi);
				z = linea[a].nz * Math.cos(fi) + linea[a].bz * Math.sin(fi);

				rete[a][b].red = (int) (255 * Math.acos(x * lrx + y * lry + z * lrz) / pi);
				rete[a][b].green = (int) (255 * Math.acos(x * lgx + y * lgy + z * lgz) / pi);
				rete[a][b].blue = (int) (255 * Math.acos(x * lbx + y * lby + z * lbz) / pi);

			} // doppio for

		for (b = 0; b <= ly + 1; b++) {
			rete[lx + 1][b].x = rete[1][b].x;
			rete[lx + 1][b].y = rete[1][b].y;
			rete[lx + 1][b].dis = rete[1][b].dis;
			rete[lx + 1][b].red = rete[1][b].red;
			rete[lx + 1][b].green = rete[1][b].green;
			rete[lx + 1][b].blue = rete[1][b].blue;
		}

		// disegna poligoni a partire dal piu' lontano
		double lontano;
		int m = 0, n = 0;
		int tot = (lx + 1) * (ly + 1);
		for (c = 1; c <= tot; c++) {
			lontano = -1;
			for (a = 1; a <= lx; a++)
				for (b = 0; b < ly; b++)
					if (rete[a][b].dis >= lontano) {
						m = a;
						n = b;
						lontano = rete[a][b].dis;
					}
			rete[m][n].dis = -1;
			if (lontano != -1)
				disegna_poligono(dc, m, n);
			// System.out.println(c+"/"+tot);
		} // for c
	}

	public c_nodo() {
		setSize(500, 500);
		// resize(500,500);
	}

	public void init() {
		for (a = 0; a < lx + 2; a++)
			for (b = 0; b < ly + 2; b++)
				rete[a][b] = new t_rete();
		for (a = 0; a < lx + 2; a++)
			linea[a] = new t_linea();

	}

	public void paint(Graphics g) {
		if (offIm != null)
			g.drawImage(offIm, 0, 0, this);
	} // fine paint

	public void update(Graphics g) {
		if (offIm != null)
			disegna_nodo(offGr);
		paint(g);
	}

} // FINE class c_nodo canvas

//-------------------principale
class nodo extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3098546524780397069L;
	c_nodo n;

	class windowE extends WindowAdapter {
		public void windowClosing(WindowEvent event) {
			dispose();
			System.exit(0);
		}
	}

	public void init() {
		addWindowListener(new windowE());
		n = new c_nodo();
		n.init();
		add(n);
		n.offIm = n.createImage(500, 500);
		n.offGr = n.offIm.getGraphics();
		n.repaint();
	}

	public static void main(String[] s) {
		nodo d = new nodo();
		d.setBackground(Color.black);
		d.setSize(500, 500);
		d.setVisible(true);
		// d.show();
		d.init();
	}

} // fine