/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//Class for RGB colors
public class RGBColor {
	private int red;
	private int green;
	private int blue;
	
	public RGBColor() {
		red = 0;
		green = 0;
		blue = 0;
	}
	
	public RGBColor(int r, int g, int b) {
		this.red = r;
		this.green = g;
		this.blue = b;
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

	public void setRed(int red) {
		this.red = red;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	
	
}
