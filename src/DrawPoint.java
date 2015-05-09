/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//Class for points on 2D coordinates
public class DrawPoint {
	private int x2d;
	private int y2d;
	private double z2d;
	private int beloPic;
	private double x3d;
	private double y3d;
	private double z3d;
	private int red;
	private int green;
	private int blue;
	
	public DrawPoint() {
		x2d = 0;
		y2d = 0;
		z2d = 0.0;
		x3d = 0.0;
		y3d = 0.0;
		z3d = 0.0;
		beloPic = -1;
		red = 0;
		green = 0;
		blue = 0;
	}
	
	public DrawPoint(int x, int y) {
		this.x2d = x;
		this.y2d = y;
		this.x3d = 0.0;
		this.y3d = 0.0;
		this.z3d = 0.0;
		beloPic = -1;
		red = 0;
		green = 0;
		blue = 0;
	}
	
	public DrawPoint(int x, int y, double z) {
		this.x2d = x;
		this.y2d = y;
		this.z2d = z;
		this.x3d = 0.0;
		this.y3d = 0.0;
		this.z3d = 0.0;
		beloPic = 0;
		red = 0;
		green = 0;
		blue = 0;
	}
	
	public DrawPoint(int x, int y, int i) {
		this.x2d = x;
		this.y2d = y;
		this.z2d = 0.0;
		this.x3d = 0.0;
		this.y3d = 0.0;
		this.z3d = 0.0;
		beloPic = i;
		red = 0;
		green = 0;
		blue = 0;
	}
	
	public DrawPoint(double x, double y, double z, int i) {
		this.x2d = 0;
		this.y2d = 0;
		this.z2d = 0.0;
		this.x3d = x;
		this.y3d = y;
		this.z3d = z;
		beloPic = i;
		red = 0;
		green = 0;
		blue = 0;
	}

	public int getX2d() {
		return x2d;
	}

	public int getY2d() {
		return y2d;
	}

	public int getBeloPic() {
		return beloPic;
	}

	public double getX3d() {
		return x3d;
	}

	public double getY3d() {
		return y3d;
	}

	public double getZ3d() {
		return z3d;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public void setX2d(int x2d) {
		this.x2d = x2d;
	}

	public void setY2d(int y2d) {
		this.y2d = y2d;
	}

	public void setBeloPic(int beloPic) {
		this.beloPic = beloPic;
	}

	public void setX3d(double x3d) {
		this.x3d = x3d;
	}

	public void setY3d(double y3d) {
		this.y3d = y3d;
	}

	public void setZ3d(double z3d) {
		this.z3d = z3d;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public double getZ2d() {
		return z2d;
	}

	public void setZ2d(double z2d) {
		this.z2d = z2d;
	}
	
}