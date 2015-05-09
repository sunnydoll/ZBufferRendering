/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//Class for clipping lines
public class LineClipping {
	public boolean leftIn;
	public boolean rightIn;
	public boolean topIn;
	public boolean bottomIn;
	
	public LineClipping () {
		leftIn = false;
		rightIn = false;
		topIn = false;
		bottomIn = false;
	}
	 public boolean topBound(DrawPoint q, DrawPoint r) {
		 if(q.getY2d() <= CG_hw5.vMaxY && r.getY2d() <= CG_hw5.vMaxY) {
			 topIn = true;
		 }
		 else {
			 topIn = false;
		 }
		 return topIn;
	 }
	 public boolean bottomBound(DrawPoint q, DrawPoint r) {
		 if(q.getY2d() >= CG_hw5.vMinY && r.getY2d() >= CG_hw5.vMinY) {
			 bottomIn = true;
		 }
		 else {
			 bottomIn = false;
		 }
		 return bottomIn;
	 }
	 public boolean leftBound(DrawPoint q, DrawPoint r) {
		 if(q.getX2d() >= CG_hw5.vMinX && r.getX2d() >= CG_hw5.vMinX) {
			 leftIn = true;
		 }
		 else {
			 leftIn = false;
		 }
		 return leftIn;
	 }
	 public boolean rightBound(DrawPoint q, DrawPoint r) {
		 if(q.getX2d() <= CG_hw5.vMaxX && r.getX2d() <= CG_hw5.vMaxX) {
			 rightIn = true;
		 }
		 else {
			 rightIn = false;
		 }
		 return rightIn;
	 }
}