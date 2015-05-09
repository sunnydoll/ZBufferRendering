/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//Class for point coordinate in 3D
public class ThreeDPoint {
	private double x;
	private double y;
	private double z;
	private int beloPic;
	
	public ThreeDPoint() {
		x = 0;
		y = 0;
		z = 0;
		beloPic = -1;
	}
	
	public ThreeDPoint(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		beloPic = -1;
	}
	
	public ThreeDPoint(double x, double y, double z, int i) {
		this.x = x;
		this.y = y;
		this.z = z;
		beloPic = i;
	}

	public int getBeloPic() {
		return beloPic;
	}

	public void setBeloPic(int beloPic) {
		this.beloPic = beloPic;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	
}
