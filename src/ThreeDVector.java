/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//Class for vectors in 3D
public class ThreeDVector {
	private double x;
	private double y;
	private double z;
	
	public ThreeDVector() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public ThreeDVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	public ThreeDVector normalize() {
		double root = Math.sqrt(x * x + y * y + z * z);
		ThreeDVector v = new ThreeDVector(x, y, z);
		if(root != 0) {
			v.x = x / root;
			v.y = y / root;
			v.z = z / root;
		}
		return v;
	}
	
	public ThreeDVector crossMul(ThreeDVector mulv) {
		ThreeDVector v = new ThreeDVector(x, y, z);
		v.x = this.y * mulv.z - this.z * mulv.y;
		v.y = this.z * mulv.x - this.x * mulv.z;
		v.z = this.x * mulv.y - this.y * mulv.x;
		return v;
	}
}