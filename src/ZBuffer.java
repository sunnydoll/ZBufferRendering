/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//Class for Z-Buffer
public class ZBuffer {
	private double z;
	private int beloPic;
	
	public ZBuffer() {
		this.z = -1;
		this.beloPic = 0;
	}
	
	public ZBuffer(double z, int i) {
		this.z = z;
		this.beloPic = i;
	}

	public double getZ() {
		return z;
	}

	public int getBeloPic() {
		return beloPic;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void setBeloPic(int beloPic) {
		this.beloPic = beloPic;
	}
}
