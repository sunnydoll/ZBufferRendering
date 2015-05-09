/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//package homeWork4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CG_hw5 {
	
	static double xyOp[][];  //Set of pairs of points
	static double xyOpSlope[];  //Set of slope of lines
	static double xOpPath[][];  //Set x-coop of points on line
	static double yOpPath[][];  //Set y-coop of points on line
	static double typeOpPath[];  //Type of slope of lines
	static double lenOpPath[];  //Number of points on line
	static int realWorldCoor[][];  //For cooperation of real world
	static int viewWorldCoor[][];  //For viewport
//	static int scanLine[][];
//	static int scanCount[];
//	static int scanLineCount[];
//	static int scanLineSet[][];
//	static int sortedInter[][];
	static int yMax = -1;
	static int yMin = 2001;  //For scan-line filling algorithm
	static double xyThreeDV[][];
	static int xyThreeDF[][];
	static double xyThreeViewD[][];
	static int xyThreeRealD[][];
	static double tempMat[][];
	static double tempMatBack[][];
	static double tempMatVer[][];
	static double tempMatVerBack[][];
	static double nPar[][];
	static double nPer[][];
	static double paraProj[][];
	static double persProj[][];
	static char outputArray[][];
	static boolean easeInput = false;
	public static List<DrawPoint> pointSet;  //Set of unclipped end points on lines
	public static List<DrawPoint> pointSetOne;  //Set of unclipped points on lines
	public static List<DrawPoint> pointSetTwo;  //Set of unclipped points on lines
	public static List<DrawPoint> pointSetThr;  //Set of unclipped points on lines
//	public static List<ThreeDPoint> pointSet3DOne;  //Set of input 3D points
//	public static List<ThreeDPoint> pointSet3DTwo;  //Set of input 3D points
//	public static List<ThreeDPoint> pointSet3DThr;  //Set of input 3D points
	public static List<DrawPoint> lineSet;   //Set of clipped points need to draw
	public static List<DrawPoint> linePointSetOne;   //Set of clipped points on the line
	public static List<DrawPoint> linePointSetTwo;   //Set of clipped points on the line
	public static List<DrawPoint> linePointSetThr;   //Set of clipped points on the line
	static String strOutput = "";  //String for output xpm file
	//string strOutputFile = "out.getX()pm";  //Output file name
	static String strInputFile = "";  //-f
	static double scalingFactor = 1.0;  //-s
	static int cClockwiseRotation = 0;  //-r
	static int xTranslation = 0;  //-m
	static int yTranslation = 0;  //-n
	static int xLowerBound = 0;  //-a
	static int yLowerBound = 0;  //-b
	static int xUpperBound = 500;  //-c
	static int yUpperBound = 500;  //-d
	static int viewXLowerBound = 0;  //-j
	static int viewYLowerBound = 0;  //-k
	static int viewXUpperBound = 500;  //-o
	static int viewYUpperBound = 500;  //-p
	static double xPRP = 0.0;  //-x
	static double yPRP = 0.0;  //-y
	static double zPRP = 1.0;  //-z
	static double xVRP = 0.0;  //-X
	static double yVRP = 0.0;  //-Y
	static double zVRP = 0.0;  //-Z
	static double xVPN = 0.0;  //-q
	static double yVPN = 0.0;  //-r
	static double zVPN = -1.0;  //-w
	static double xVUP = 0.0;  //-Q
	static double yVUP = 1.0;  //-R
	static double zVUP = 0.0;  //-W
	static double uMinVRC = -0.7;  //-u
	static double vMinVRC = -0.7;  //-v
	static double uMaxVRC = 0.7;  //-U
	static double vMaxVRC = 0.7;  //-V
	static int parallelProj = 0;  //-P  If this flag is not presented (be 0), use perspective projection, else (be 1), use parallel projection
	static double backFace = -0.6;  //-B
	static double frontFace = 0.6;  //-F
	static String fstFile = "bound-sprellpsd.smf";  //-1
	static String sndFile = "";  //-2
	static String trdFile = "";  //-3
	String strPS = "";  //Content string in the .ps file
	String strSetPS[];  //Split strPS string
	int stIndex = 0;  
	int edIndex = 0;
	int stIndexOp = 0;
	String tempStrOp = "";
	int xyLnIndex = 0;  //Number of lines
	int xyThreeLnIndex = 0;
	int xyThreeLnIndexV = 0;
	int xyThreeLnIndexF = 0;
	int xyOpIndex = 0;  //Index for x,y-coop, for line command
	int negNum = 0;		//If it is negtive number of the input
	int fstPointIndex = 0;  //Temp index for first point of moveto and lineto command
	int sndPointIndex = 0;  //Temp index for second point of moveto and lineto command
	int countIndex = 0;  //Number of lines in .ps file
	String strLength = "";  //Length of pic
	String strWidth = "";  //Width of pic
	int lineType = 0;  //Type of command in .ps file. 1 for line, 2 for moveto, 3 for lineto
	static double viewX = 0;
	static double viewY = 0;
	static double tempX = 0;
	static double tempY = 0;
	static int realX = 0;
	static int realY = 0;
	static double valueF[][];
	static double valueZ[][];
	static int vMinX;
	static int vMaxX;
	static int vMinY;
	static int vMaxY;
	static ZBuffer zBuffer[][];
	static RGBColor frameBuffer[][];
	static int numColors;
	static double zmin;
	
	public static void main(String[] args) throws Exception {
		pointSet = new ArrayList<DrawPoint>();
		pointSetOne = new ArrayList<DrawPoint>();
		pointSetTwo = new ArrayList<DrawPoint>();
		pointSetThr = new ArrayList<DrawPoint>();
		lineSet = new ArrayList<DrawPoint>();
		linePointSetOne = new ArrayList<DrawPoint>();
		linePointSetTwo = new ArrayList<DrawPoint>();
		linePointSetThr = new ArrayList<DrawPoint>();
//		pointSet3DOne = new ArrayList<ThreeDPoint>();
//		pointSet3DTwo = new ArrayList<ThreeDPoint>();
//		pointSet3DThr = new ArrayList<ThreeDPoint>();
		frameBuffer = new RGBColor [501][501];
		zBuffer = new ZBuffer [501][501];
		outputArray = new char [501][501];  //Initialization of output cells		
		for (int i = 0; i <= 500; i++) {
			for(int j = 0; j <= 500; j++) {
				outputArray[i][j] = ' ';
				RGBColor color = new RGBColor(0, 0, 0);
				frameBuffer[i][j] = color;
				ZBuffer zb = new ZBuffer();
				zBuffer[i][j] = zb;
			}
		}
		numColors = 62;
		
		if(easeInput == true) {
			strInputFile = "";
			scalingFactor = 1.0;
			cClockwiseRotation = 0;
			xTranslation = 0;
			yTranslation = 0;
			xLowerBound = 0;
			yLowerBound = 0;
			xUpperBound = 500;
			yUpperBound = 500;
			viewXLowerBound = 0;
			viewYLowerBound = 0;
			viewXUpperBound = 500;
			viewYUpperBound = 500;
			//strOutputFile = "out.getX()pm";
			xPRP = 0.0;
			yPRP = 0.0;
			zPRP = 1.0;
			xVRP = 0.0;
			yVRP = 0.0;
			zVRP = 0.0;
			xVPN = 0.0; 
		    yVPN = 0.0;
			zVPN = 1.0;
			xVUP = 0.0;
			yVUP = 1.0;
			zVUP = 0.0;
			uMinVRC = -0.7;
			vMinVRC = -0.7;
			uMaxVRC = 0.7;
			vMaxVRC = 0.7;
			parallelProj = 0;
			backFace = -0.6;
			frontFace = 0.6;
			fstFile = "bound-sprellpsd.smf";
			sndFile = "";
			trdFile = "";
		}
		else {
			for (int i = 0; i < args.length; i++) {
				if(args[i].equals("-f")){
					strInputFile = args[i + 1];
				}
				else if(args[i].equals("-s")){
					scalingFactor = Double.parseDouble(args[i + 1]);
				}
				/*else if(tempStr == "-r")
				{
					cClockwiseRotation = Integer.parseInt(args[i + 1]);
				}*/
				else if(args[i].equals("-m")) {
					xTranslation = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-n")) {
					yTranslation = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-a")) {
					xLowerBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-b")) {
					yLowerBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-c")) {
					xUpperBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-d")) {
					yUpperBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-j")) {
					viewXLowerBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-k")) {
					viewYLowerBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-o")) {
					viewXUpperBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-p")) {
					viewYUpperBound = Integer.parseInt(args[i + 1]);
				}
				else if(args[i].equals("-x")) {
					xPRP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-y")) {
					yPRP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-z")) {
					zPRP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-X")) {
					xVRP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-Y")) {
					yVRP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-Z")) {
					zVRP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-q")) {
					xVPN = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-r")) {
					yVPN = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-w")) {
					zVPN = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-Q")) {
					xVUP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-R")) {
					yVUP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-W")) {
					zVUP = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-u")) {
					uMinVRC = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-v")) {
					vMinVRC = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-U")) {
					uMaxVRC = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-V")) {
					vMaxVRC = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-F")) {
					frontFace = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-B")) {
					backFace = Double.parseDouble(args[i + 1]);
				}
				else if(args[i].equals("-P")) {
					parallelProj = 1;
				}
				else if(args[i].equals("-1")) {
					fstFile = args[i + 1];
				}
				else if(args[i].equals("-2")) {
					sndFile = args[i + 1];
				}
				else if(args[i].equals("-3")) {
					trdFile = args[i + 1];
				}
			}
		}			
		
		vMinX = viewXLowerBound;
		vMaxX = viewXUpperBound;
		vMinY = viewYLowerBound;
		vMaxY = viewYUpperBound;

		if(strInputFile != "" && strInputFile.isEmpty() == false) {
			readAnalysisPoint(strInputFile, 1);
		}
		if(fstFile != "" && fstFile.isEmpty() == false) {
			readAnalysisPoint(fstFile, 1);
//			System.out.println(linePointSetOne.size());
		}
		if(sndFile != "" && sndFile.isEmpty() == false) {
			readAnalysisPoint(sndFile, 2);
//			System.out.println(linePointSetTwo.size());
		}
		if(trdFile != "" && trdFile.isEmpty() == false) {
			readAnalysisPoint(trdFile, 3);
//			System.out.println(linePointSetThr.size());
		}
		
//		for(int i = 0; i <= 500; i++) {
//			for(int j = 0; j <= 500; j++) {
//				if(zBuffer[i][j].getBeloPic() == 0) {
//					signPointColor(i, j, 0, '@');
//				}
//				else {
//					double z = zBuffer[i][j].getZ();
//					int index = zBuffer[i][j].getBeloPic();
//					char c = pointColor(z, index);
//					signPointColor(i, j, index, c);
//				}
////				else if(zBuffer[i][j].getBeloPic() == 1) {
////					signPointColor(i, j, 1);
////				}
////				else if(zBuffer[i][j].getBeloPic() == 2) {
////					signPointColor(i, j, 2);
////				}
////				else if(zBuffer[i][j].getBeloPic() == 3) {
////					signPointColor(i, j, 3);
////				}
////				else {
////					signPointColor(i, j, 0);
////				}
//			}
//		}
		
		for(int i = 0; i <= 500; i++) {
			for(int j = 0; j <= 500; j++) {
				if(zBuffer[i][j].getBeloPic() == 0) {
					signPointColor(i, j, 0, '@');
				}
			}
		}
		
		//Outputting
		System.out.println("/* XPM */");
		System.out.println("static char *quad_bw[] = {");
		System.out.println("/* columns rows colors chars-per-pixel */");
//		System.out.println("\" 501 501 5 1\",");
		System.out.println("\" 501 501 " + numColors + " 1\",");
		System.out.println("/* colors */");
		System.out.println("\"@ c #000000\",");
		System.out.println("\"  c #ffffff\",");
		System.out.println("\"0 c #0d0000\",");
		System.out.println("\"1 c #1a0000\",");
		System.out.println("\"2 c #270000\",");
		System.out.println("\"3 c #340000\",");
		System.out.println("\"4 c #410000\",");
		System.out.println("\"5 c #4e0000\",");
		System.out.println("\"6 c #5b0000\",");
		System.out.println("\"7 c #680000\",");
		System.out.println("\"8 c #750000\",");
		System.out.println("\"9 c #820000\",");
		System.out.println("\"! c #8f0000\",");
		System.out.println("\"# c #9c0000\",");
		System.out.println("\"$ c #a90000\",");
		System.out.println("\"% c #b60000\",");
		System.out.println("\"^ c #c30000\",");
		System.out.println("\"& c #d00000\",");
		System.out.println("\"* c #dd0000\",");
		System.out.println("\"( c #ea0000\",");
		System.out.println("\") c #f70000\",");
		System.out.println("\"_ c #ff0000\",");
		System.out.println("\"a c #000d00\",");
		System.out.println("\"b c #001a00\",");
		System.out.println("\"c c #002700\",");
		System.out.println("\"d c #003400\",");
		System.out.println("\"e c #004100\",");
		System.out.println("\"f c #004e00\",");
		System.out.println("\"g c #005b00\",");
		System.out.println("\"h c #006800\",");
		System.out.println("\"i c #007500\",");
		System.out.println("\"j c #008200\",");
		System.out.println("\"k c #008f00\",");
		System.out.println("\"l c #009c00\",");
		System.out.println("\"m c #00a900\",");
		System.out.println("\"n c #00b600\",");
		System.out.println("\"o c #00c300\",");
		System.out.println("\"p c #00d000\",");
		System.out.println("\"q c #00dd00\",");
		System.out.println("\"r c #00ea00\",");
		System.out.println("\"s c #00f700\",");
		System.out.println("\"t c #00ff00\",");
		System.out.println("\"A c #00000d\",");
		System.out.println("\"B c #00001a\",");
		System.out.println("\"C c #000027\",");
		System.out.println("\"D c #000034\",");
		System.out.println("\"E c #000041\",");
		System.out.println("\"F c #00004e\",");
		System.out.println("\"G c #00005b\",");
		System.out.println("\"H c #000068\",");
		System.out.println("\"I c #000075\",");
		System.out.println("\"J c #000082\",");
		System.out.println("\"K c #00008f\",");
		System.out.println("\"L c #00009c\",");
		System.out.println("\"M c #0000a9\",");
		System.out.println("\"N c #0000b6\",");
		System.out.println("\"O c #0000c3\",");
		System.out.println("\"P c #0000d0\",");
		System.out.println("\"Q c #0000dd\",");
		System.out.println("\"R c #0000ea\",");
		System.out.println("\"S c #0000f7\",");
		System.out.println("\"T c #0000ff\",");		
		for(int i = 0; i <= 500; i++) {
			System.out.print("\"" + outputArray[0][500 - i]);
			if(i == yUpperBound - yLowerBound) {
				for(int j = 1; j <= 500; j++) {
					if(j == 500) {
//						strOutput += outputArray[j][500 - i] + "\"};";
						System.out.println(outputArray[j][500 - i] + "\"};");
					}
					else {
//						strOutput += outputArray[j][500 - i];
						System.out.print(outputArray[j][500 - i]);
					}
				}
			}
			else{
				for(int j = 1; j <= 500; j ++) {
					if(j == 500) {
//						strOutput += outputArray[j][500 -i] + "\",";
						System.out.println(outputArray[j][500 -i] + "\",");
					}
					else {
//						strOutput += outputArray[j][500 - i];
						System.out.print(outputArray[j][500 - i]);
					}
				}
			}
		}
	}
	
	public static void signPointColor(int x, int y, int index, char c) {
		if(x >= vMinX && x <= vMaxX && y >= vMinY && y <= vMaxY) {
			outputArray[x][y] = c;
//			if(index == 0) {
//				outputArray[x][y] = '@';
//			}
//			else if(index == 1) {
//				outputArray[x][y] = '_';
//			}
//			else if(index == 2) {
//				outputArray[x][y] = 't';
//			}
//			else if(index == 3) {
//				outputArray[x][y] = 'T';
//			}
//			outputArray[x][y] = '@';
		}
	}

	public static void signPoint(int x, int y, int index){		
		//Fill up the points of picture
		if(x >= vMinX && x <= vMaxX && y >= vMinY && y <= vMaxY) {
			if(index == 1) {
				
			}
			else if(index == 2) {
				
			}
			else if(index == 3) {
				
			}
			outputArray[x][y] = '@';
		}
//		outputArray[x][y] = '@';
	}	

//	public static void drawLine(DrawPoint q, DrawPoint r) {
//		int typeOpPath;
//		double slope;
//		
//		//Creating lines in different situations, using bresenham algorithm
//		if(q != null && r != null) {
//			// if r.x < q.x, swap r and q
//			int sign = calXpoint(q.x, r.x);
//			if(sign < 0) { 
//				int temp;			
//				temp = r.getX();
//				r.setX(q.getX());
//				q.setX(temp);			
//				temp = r.getY();
//				r.setY(q.getY());
//				q.setY(temp);
//			}
//			slope = calSlope(q.getX(), q.getY(), r.getX(), r.getY());
//			if(slope >= 0 && slope <= 1)
//			{
//				typeOpPath = 0;
//				//No need for modification
//				bresenham(q.getX(), q.getY(), r.getX(), r.getY(), typeOpPath);
//			}
//			else if(slope >= -1 && slope < 0)
//			{
//				typeOpPath = 1;
//				//Switch y1 and y2 to make 0<=slope<=1
//				bresenham(q.getX(), q.getY(), r.getX(), r.getY(), typeOpPath);
//			}
//			else if(slope> 1)
//			{
//				typeOpPath = 2;
//				//(y1, x1)(y2, x2) to make 0<=slope<=1
//				bresenham(q.getX(), q.getY(), r.getX(), r.getY(), typeOpPath);
//			}
//			else if(slope < -1)
//			{
//				typeOpPath = 3;
//				//(y2, -x2) and (y1, -x1) to make 0<=slope<=1
//				bresenham(q.getX(), q.getY(), r.getX(), r.getY(), typeOpPath);
//			}
//		}
//	}
	
	public static void drawLine(DrawPoint q, DrawPoint r, int index) {
		int dx, dy, D, x, y;
		double slope;
		
		//Creating lines in different situations, using bresenham algorithm
		if(q != null && r != null) {
			// if r.x < q.x, swap r and q
			int sign = calXpoint(q.getX2d(), r.getX2d());
			if(sign < 0) { 
				int temp;			
				temp = r.getX2d();
				r.setX2d(q.getX2d());
				q.setX2d(temp);			
				temp = r.getY2d();
				r.setY2d(q.getY2d());
				q.setY2d(temp);
			}		
			dx = r.getX2d() - q.getX2d();
			dy = r.getY2d() - q.getY2d();	
			y = q.getY2d();
			x = q.getX2d();		
			slope = calSlope(q.getX2d(), q.getY2d(), r.getX2d(), r.getY2d());
			if(dx == 0) { 				
				//if parallel to y axis, which means infinity slope
				for(y = q.getY2d(); y <= r.getY2d(); y++) {
//					signPoint(x, y, index);
					DrawPoint p = new DrawPoint(x, y, index);
					if(index == 1) {
						linePointSetOne.add(p);
					}
					else if(index == 2) {
						linePointSetTwo.add(p);
					}
					else if(index == 3) {
						linePointSetThr.add(p);
					}
				}
			}		
			else if(slope == 0) { 		
				//slope = 0
				for(x = q.getX2d(); x <= r.getX2d(); x++) {
//					signPoint(x, y, index);
					DrawPoint p = new DrawPoint(x, y, index);
					if(index == 1) {
						linePointSetOne.add(p);
					}
					else if(index == 2) {
						linePointSetTwo.add(p);
					}
					else if(index == 3) {
						linePointSetThr.add(p);
					}
				}
			}	
			else if (slope> 1) { 
				//slope > 1
				D = -2 * dx + dy;
				for(y = q.getY2d(); y <= r.getY2d(); y++) {
//					signPoint(x, y, index);
					DrawPoint p = new DrawPoint(x, y, index);
					if(index == 1) {
						linePointSetOne.add(p);
					}
					else if(index == 2) {
						linePointSetTwo.add(p);
					}
					else if(index == 3) {
						linePointSetThr.add(p);
					}
					if(D > 0) {
						D += - 2 * dx;
					}
					else {
						D += 2 * (- dx + dy);
						x++;
					}
				}
			}		
			else if (slope < -1) {
				// slope < -1
				D = 2 * dx + dy; 					
				for(y = q.getY2d(); y >= r.getY2d(); y--) {
//					signPoint(x, y, index);
					DrawPoint p = new DrawPoint(x, y, index);
					if(index == 1) {
						linePointSetOne.add(p);
					}
					else if(index == 2) {
						linePointSetTwo.add(p);
					}
					else if(index == 3) {
						linePointSetThr.add(p);
					}
					if(D <= 0) {
						D += 2 * dx;
					}
					else {
						D += 2 * (dy + dx);
						x++;
					}
				}
			}
			else if(slope > 0 && slope <= 1) { 
				//0 < slope <= 1
				D = 2 * dy - dx;
				for(x = q.getX2d(); x <= r.getX2d(); x++) {
//					signPoint(x, y, index);
					DrawPoint p = new DrawPoint(x, y, index);
					if(index == 1) {
						linePointSetOne.add(p);
					}
					else if(index == 2) {
						linePointSetTwo.add(p);
					}
					else if(index == 3) {
						linePointSetThr.add(p);
					}
					if(D <= 0) {
						D += 2 * dy;
					}
					else {
						D += 2 * (dy - dx);
						y++;
					}
				}
			}
			else if (slope >= -1 && slope < 0) { 
				//-1 <= slope < 0
				D = 2 * dy + dx;
				for(x = q.getX2d(); x <= r.getX2d(); x++) {
//					signPoint(x, y, index);
					DrawPoint p = new DrawPoint(x, y, index);
					if(index == 1) {
						linePointSetOne.add(p);
					}
					else if(index == 2) {
						linePointSetTwo.add(p);
					}
					else if(index == 3) {
						linePointSetThr.add(p);
					}
					if(D > 0) {
						D += 2 * dy;
					}
					else {
						D += 2 * (dy + dx);
						y--;
					}
				}
			}
		}
	}
	
	public static double calSlope(int qx, int qy, int rx, int ry)
	{
		//Calculate the slope of the line
		double slope = 0.0;
		if(rx - qx != 0) {
			slope = (double) (ry - qy) / (rx - qx);
		}
		else {
			if(ry - qy < 0)
				slope = -1000;
			else if(ry - qy > 0)
				slope = 1000;
			else if (ry - qy == 0)
				slope = 0;
		}
		return slope;
	}

	public static int calXpoint(int qx, int rx)
	{
		//Calculate the distance of points of x-coop
		if(rx - qx < 0)
		{
			return -1;
		}
		else if(rx - qx == 0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public static void transform(double x, double y, int sign, int index, double scal, int rota, int xTran, int yTran, int umin, int vmin, int umax, int vmax, int xl, int yl, int xu, int yu)
	{
		//Transformation of lines
//		double xScale = (double)(umax - umin) / (xu - xl);
//		double yScale = (double)(vmax - vmin) / (yu - yl);
		double tempX = x * scal;
		double tempY = y * scal;;
		double cosValue = Math.cos((rota*Math.PI)/180.0);
		double sinValue = Math.sin((rota*Math.PI)/180.0);
		x = tempX * cosValue - tempY * sinValue + xTran;
		y = tempX * sinValue + tempY * cosValue + yTran;
		/*x = (x - xl) * xScale + umin;
		y = (y - yl) * yScale + vmin;*/
	}
	
	public static void writePixel(int x, int y, int sign)
	{
		//Generating mapping point of the line
		switch (sign)
		{
			case 0:
				signPoint(x, y, sign);
//				xOpPath[index][length] = x;
//				yOpPath[index][length] = y;
				break;
			case 1:
				signPoint(x, -y, sign);
//				xOpPath[index][length] = x;
//				yOpPath[index][length] = -y;
				break;
			case 2:	
				signPoint(y, x, sign);
//				xOpPath[index][length] = y;
//				yOpPath[index][length] = x;
				break;
			case 3:
				signPoint(-y, x, sign);
//				xOpPath[index][length] = -y;
//				yOpPath[index][length] = x;
				break;
			/*case 4:
			case 5:
				xOpPath[index][length] = x;
				yOpPath[index][length] = y;
				break;*/
			default:
//				xOpPath[index][length] = 0;
//				yOpPath[index][length] = 0;
				System.out.println("Points failed");
				break;
		}
	}
	
	public String readFile(String strPath)
	{
//		//Read the ps file
		String strPS = "";
//		char chStrPath[20];
//		strcpy(chStrPath, strPath.c_str());
//		char buffer[256];
//		ifstream hsfile;
//		hsfile.open(chStrPath);
//		if(!hsfile){
//	        cout << "Unable to open the file";
//	        exit(1);  // terminate with error
//		}
//		while (!hsfile.eof())
//		{
//			//strPS = hsfile.get();
//			hsfile.getline(buffer,50);
//			//cout << buffer << endl;
//			strPS += buffer;
//			strPS += "\n";
//		}
//		hsfile.close();
		return strPS;
	}
	
	public static void bresenham(int qx, int qy, int rx, int ry, int sign)
	{
		//Bresenham's algorithm
//		int length_line = 0;
		int dx, dy, D, x, y = 0;
		//cout<<qx<<","<<qy<<" "<<rx<<","<<ry<<endl;
		dx = rx - qx;
		dy = ry - qy;
		D = 2 * dy - dx;
		y = qy;
		for (x = qx; x <= rx; x++)
		{
			writePixel(x, y, sign);
			//cout << x << "," << y << endl;
			if(D <= 0)
			{
				D += 2 * dy;
			}
			else
			{
				D += 2 * (dy -dx) ;
				y++;
			}
//			length_line++;
		}
//		lenOpPath[index] = length_line;
	}
	
	public static void readAnalysisPoint(String strInputFile, int index) throws FileNotFoundException {
		pointSet.clear();
		lineSet.clear();
		linePointSetOne.clear();
		linePointSetTwo.clear();
		linePointSetThr.clear();
		
		Scanner scan = new Scanner(new File(strInputFile));
		
		//View port
		viewX = (double)(viewXUpperBound - viewXLowerBound) / 2;
		viewY = (double)(viewYUpperBound - viewYLowerBound) / 2;
		
		MatrixUtility paraProj = new MatrixUtility(4, 4);
		MatrixUtility persProj = new MatrixUtility(4, 4);	
		MatrixUtility nPer = new MatrixUtility(4, 4);
		MatrixUtility nPar = new MatrixUtility(4, 4);
		
		ThreeDVector vectorPRP = new ThreeDVector(xPRP, yPRP, zPRP); //PRP
		ThreeDVector vectorVRP = new ThreeDVector(xVRP, yVRP, zVRP); //VRP
		ThreeDVector vectorVPN = new ThreeDVector(xVPN, yVPN, zVPN); //VPN
		ThreeDVector vectorVUP = new ThreeDVector(xVUP, yVUP, zVUP); //VUP
		
		//Translate VRP to the origin
		double[][] vTVRP = {
				{1, 0, 0, -vectorVRP.getX()},
				{0, 1, 0, -vectorVRP.getY()},
				{0, 0, 1, -vectorVRP.getZ()},
				{0, 0, 0, 1}
				};
		MatrixUtility mTVRP = new MatrixUtility(vTVRP);

		//Rotate
		ThreeDVector mn = vectorVPN.normalize();
		ThreeDVector mu = vectorVUP.crossMul(mn);
		mu = mu.normalize();
		ThreeDVector mv = mn.crossMul(mu);
		double[][] vR = {
				{mu.getX(), mu.getY(), mu.getZ(), 0},
				{mv.getX(), mv.getY(), mv.getZ(), 0},
				{mn.getX(), mn.getY(), mn.getZ(), 0},
				{0, 0, 0, 1}
				};
		MatrixUtility mR = new MatrixUtility(vR);
		
		//Shear
		double[][] vSHpar = {
				{1, 0, ((uMaxVRC + uMinVRC) / 2 - vectorPRP.getX()) / vectorPRP.getZ(), 0},
				{0, 1, ((vMaxVRC + vMinVRC) / 2 - vectorPRP.getY()) / vectorPRP.getZ(), 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
				};
		MatrixUtility mSHpar = new MatrixUtility(vSHpar);
		
		//Translate and scale for parallel projection
		double[][] vTpar = {
				{1, 0, 0, -(uMaxVRC + uMinVRC) / 2},
				{0, 1, 0, -(vMaxVRC + vMinVRC) / 2},
				{0, 0, 1, -frontFace},
				{0, 0, 0, 1}
				};
		MatrixUtility mTpar = new MatrixUtility(vTpar);		
		double[][] vSpar = {
				{2 / (uMaxVRC - uMinVRC), 0, 0, 0},
				{0, 2 / (vMaxVRC - vMinVRC), 0, 0},
				{0, 0, 1 / (frontFace - backFace), 0},
				{0, 0, 0, 1}
				};
		MatrixUtility mSpar = new MatrixUtility(vSpar);
		
		//Translate and scale for perspective projection
		double[][] vTPRP = {
				{1, 0, 0, -vectorPRP.getX()},
				{0, 1, 0, -vectorPRP.getY()},
				{0, 0, 1, -vectorPRP.getZ()},
				{0, 0, 0, 1}
				};
		MatrixUtility mTPRP = new MatrixUtility(vTPRP);		
		double[][] vSper = {
				{2 * (-vectorPRP.getZ()) / ((uMaxVRC - uMinVRC) * ((-vectorPRP.getZ()) + backFace)), 0, 0, 0},
				{0, 2 * (-vectorPRP.getZ()) / ((vMaxVRC - vMinVRC) * ((-vectorPRP.getZ()) + backFace)), 0, 0},
				{0, 0, -1 / ((-vectorPRP.getZ()) + backFace), 0},
				{0, 0, 0, 1}
				};
		MatrixUtility mSper = new MatrixUtility(vSper);
		
		//Final matrix for perspective projection
		double[][] vMper = {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, -1, 0}
				};		
		MatrixUtility mMper = new MatrixUtility(vMper);
		
		//Final matrix for parallel projection
		double[][] vMort = {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 1}
				};
		MatrixUtility mMort = new MatrixUtility(vMort);		
		
		if (parallelProj == 0) {
//			Nper = (Sper * (SHpar * (T(-PRP) * (R * T(-VRP)))))
			nPer = mSper.multiplication(mSHpar.multiplication(mTPRP.multiplication(mR.multiplication(mTVRP))));
			persProj = mMper.multiplication(mSper.multiplication(mSHpar.multiplication(mTPRP.multiplication(mR.multiplication(mTVRP)))));
		}
		else {
//			Npar = (Spar * (Tpar * (SHpar * (R * T(-VRP))))) 
			nPar = mSpar.multiplication(mTpar.multiplication(mSHpar.multiplication(mR.multiplication(mTVRP))));
			paraProj = mMort.multiplication(mSpar.multiplication(mTpar.multiplication(mSHpar.multiplication(mR.multiplication(mTVRP)))));
		}

		while (scan.hasNextLine()) {
			String bufferLine = scan.nextLine();
			
			if (bufferLine.trim().isEmpty() == false) {
				String[] inputLines = bufferLine.trim().split("\\s+");

				if (inputLines[0].equals("v")) {
					//Coordinate of points
					double x = Double.parseDouble(inputLines[1]);
					double y = Double.parseDouble(inputLines[2]);
					double z = Double.parseDouble(inputLines[3]);
					
//					ThreeDPoint p3d = new ThreeDPoint(x, y, z, index);
//					if(index == 1) {
//						pointSet3DOne.add(p3d);
//					}
//					else if(index == 2) {
//						pointSet3DTwo.add(p3d);
//					}
//					else if(index == 3) {
//						pointSet3DThr.add(p3d);
//					}
					DrawPoint p3d = new DrawPoint(x, y, z, index);
					if(index == 1) {
						pointSetOne.add(p3d);
					}
					else if(index == 2) {
						pointSetTwo.add(p3d);
					}
					else if(index == 3) {
						pointSetThr.add(p3d);
					}
					
					double[][] vP = {{x},{y},{z},{1}};  //Homogeneous matrix vector					
					MatrixUtility homoMatrix = new MatrixUtility(vP);  //Homogeneous matrix
					double zvalue = 0;
					//Projection based on the result of matrix above in 2 types of projection
					if (parallelProj == 0) {
						MatrixUtility interMU = persProj.multiplication(homoMatrix);
						MatrixUtility mper =nPer.multiplication(homoMatrix);
						double divider = interMU.getCell(3, 0);
						tempX = interMU.getCell(0, 0) / divider;
						tempY = interMU.getCell(1, 0) / divider;
						zvalue = mper.getCell(2, 0);
						zmin = (zPRP - frontFace) / (backFace - zPRP);
					}
					else {
						MatrixUtility interMU = paraProj.multiplication(homoMatrix);
						MatrixUtility mpar =nPar.multiplication(homoMatrix);
						tempX = interMU.getCell(0, 0);
						tempY = interMU.getCell(1, 0);
						zvalue = mpar.getCell(2, 0);
						zmin = 0.0;
					}
					//Convert into 2D coordinates
					realX = (int)((double)((tempX + 1) * viewX) + viewXLowerBound);
					realY = (int)((double)((tempY + 1) * viewY) + viewYLowerBound);
					
//					DrawPoint p2d = new DrawPoint(realX, realY, index);
//					pointSet.add(p2d);
					if(index == 1) {
//						pointSetOne.add(p2d);
						pointSetOne.get(pointSetOne.size() - 1).setX2d(realX);
						pointSetOne.get(pointSetOne.size() - 1).setY2d(realY);
						pointSetOne.get(pointSetOne.size() - 1).setZ2d(zvalue);
						pointSet.add(pointSetOne.get(pointSetOne.size() - 1));
					}
					else if(index == 2) {
//						pointSetTwo.add(p2d);
						pointSetTwo.get(pointSetTwo.size() - 1).setX2d(realX);
						pointSetTwo.get(pointSetTwo.size() - 1).setY2d(realY);
						pointSetTwo.get(pointSetTwo.size() - 1).setZ2d(zvalue);
						pointSet.add(pointSetTwo.get(pointSetTwo.size() - 1));
					}
					else if(index == 3) {
//						pointSetThr.add(p2d);
						pointSetThr.get(pointSetThr.size() - 1).setX2d(realX);
						pointSetThr.get(pointSetThr.size() - 1).setY2d(realY);
						pointSetThr.get(pointSetThr.size() - 1).setZ2d(zvalue);
						pointSet.add(pointSetThr.get(pointSetThr.size() - 1));
					}
				}				
				else if (inputLines[0].equals("f")) {
					//Face of points, which is made up of 3 lines
					int f1 = Integer.parseInt(inputLines[1]) - 1;
					int f2 = Integer.parseInt(inputLines[2]) - 1;
					int f3 = Integer.parseInt(inputLines[3]) - 1;
					DrawPoint p1, p2, p3;
					p1 = pointSet.get(f1);
					p2 = pointSet.get(f2);
					p3 = pointSet.get(f3);
					
					//Back-face culling
//					double[][] direMatrix = {
//							{p2.getX() - p1.getX(), p3.getX() - p1.getX(), p1.getX()},
//							{p2.getY() - p1.getY(), p3.getY() - p1.getY(), p1.getY()},
//							{0, 0, 1},
//							};					
//					MatrixUtility interMU = new MatrixUtility(direMatrix);
//					if(interMU.cullModel() > 0) {
//						lineSet.add(p1);
//						lineSet.add(p2);
//						lineSet.add(p3);
//						lineSet.add(p1);
//					}					
					lineSet.add(p1);
					lineSet.add(p2);
					lineSet.add(p3);
					lineSet.add(p1);

					if(!lineSet.isEmpty()) {
						for(int i = 0; i < lineSet.size() - 1; i++) {
							int tx = 0;
							int ty = 0;
							tx = lineSet.get(i).getX2d();
							ty = lineSet.get(i).getY2d();
							DrawPoint q = new DrawPoint(tx, ty);
							tx = lineSet.get(i + 1).getX2d();
							ty = lineSet.get(i + 1).getY2d();
							DrawPoint r = new DrawPoint(tx, ty);
							drawLine(q, r, index);
						}
//						System.out.println(linePointSetOne.size());
//						System.out.println(p1.getY2d());
//						System.out.println(p2.getY2d());
//						System.out.println(p3.getY2d());
//						for(int c = 0; c < linePointSetOne.size(); c++) {
//							System.out.println(linePointSetOne.get(c).getX2d() + " " + linePointSetOne.get(c).getY2d());
//						}
//						scanLine(p1, p2, p3, index, zmin);
						scanLineFill(lineSet, index, zmin);
					}
					lineSet.clear();
					linePointSetOne.clear();
					linePointSetTwo.clear();
					linePointSetThr.clear();
				}
			}
			
		}
		scan.close();
	}
	
	public static void scanLine(DrawPoint p1, DrawPoint p2, DrawPoint p3, int index, double zmin) {
		int yMax = -2000;
		int yMin = 2000;
		int xMin, xMax;
		DrawPoint z1 = new DrawPoint();
		DrawPoint z2 = new DrawPoint();
		DrawPoint z3 = new DrawPoint();
		DrawPoint za, zb, zp;
		double z1XValue, z1YValue, z1ZValue;
		double z2XValue, z2YValue, z2ZValue;
		double z3XValue, z3YValue, z3ZValue;
		double zaXValue, zaYValue, zaZValue;
		double zbXValue, zbYValue, zbZValue;
		double zpZValue;
		if(p1.getY2d() < yMin) {
			yMin = p1.getY2d();
		}
		if(p2.getY2d() < yMin) {
			yMin = p2.getY2d();
		}
		if(p3.getY2d() < yMin) {
			yMin = p3.getY2d();
		}
		if(p1.getY2d() > yMax) {
			yMax = p1.getY2d();
		}
		if(p2.getY2d() > yMax) {
			yMax = p2.getY2d();
		}
		if(p3.getY2d() > yMax) {
			yMax = p3.getY2d();
		}		
		if(p1.getY2d() == yMin) {
			z3 = p1;
			if(p2.getY2d() == yMax) {
				z1 = p2;
				z2 = p3;
			}
			else if(p3.getY2d() == yMax) {
				z1 = p3;
				z2 = p2;
			}
		}
		else if(p2.getY2d() == yMin) {
			z3 = p2;
			if(p1.getY2d() == yMax) {
				z1 = p1;
				z2 = p3;
			}
			else if(p3.getY2d() == yMax) {
				z1 = p3;
				z2 = p1;
			}
		}
		else if(p3.getY2d() == yMin) {
			z3 = p3;
			if(p1.getY2d() == yMax) {
				z1 = p1;
				z2 = p2;
			}
			else if(p2.getY2d() == yMax) {
				z1 = p2;
				z2 = p1;
			}
		}
		z1XValue = z1.getX2d();
		z1YValue = z1.getY2d();
		z1ZValue = z1.getZ2d();
		z2XValue = z2.getX2d();
		z2YValue = z2.getY2d();
		z2ZValue = z2.getZ2d();
		z3XValue = z3.getX2d();
		z3YValue = z3.getY2d();
		z3ZValue = z3.getZ2d();
		System.out.println(z1XValue + " " + z1YValue + " " + z1ZValue);
		List<DrawPoint> scanLinePointSet = new ArrayList<DrawPoint>();
		for(int ys = yMax; ys > yMin; ys--) {
			//Scan line
			scanLinePointSet.clear();
			if(index == 1) {
				for(int k = 0; k < linePointSetOne.size(); k++) {
					if(linePointSetOne.get(k).getY2d() == ys) {
						DrawPoint intersecPoint = linePointSetOne.get(k);
						scanLinePointSet.add(intersecPoint);
					}
				}
			}
			else if(index == 2) {
				for(int k = 0; k < linePointSetTwo.size(); k++) {
					if(linePointSetTwo.get(k).getY2d() == ys) {
						DrawPoint intersecPoint = linePointSetTwo.get(k);
						scanLinePointSet.add(intersecPoint);
					}
				}
			}
			else if(index == 3) {
				for(int k = 0; k < linePointSetThr.size(); k++) {
					if(linePointSetThr.get(k).getY2d() == ys) {
						DrawPoint intersecPoint = linePointSetThr.get(k);
						scanLinePointSet.add(intersecPoint);
					}
				}
			}
			if(scanLinePointSet.size() == 1) {
				zp = scanLinePointSet.get(0);
				zpZValue = zp.getZ2d();
				if(zBuffer[zp.getX2d()][zp.getY2d()].getZ() < zpZValue && zpZValue >= backFace && zpZValue <= frontFace) {
//				if(zBuffer[zp.getX2d()][zp.getY2d()].getZ() < zpZValue) {
					zBuffer[zp.getX2d()][zp.getY2d()].setZ(zpZValue);
					zBuffer[zp.getX2d()][zp.getY2d()].setBeloPic(index);
				}
//				signPoint(zp.getX2d(), ys, index);				
			}
			else if(scanLinePointSet.size() > 1) {
				for(int i = scanLinePointSet.size() - 1; i >= 0; i--) {
					for(int j = 0; j < i; j++) {
						DrawPoint tPoint = scanLinePointSet.get(j);
						DrawPoint tPointPlus = scanLinePointSet.get(j + 1);
						if(tPoint.getX2d() > tPointPlus.getX2d()) {
							scanLinePointSet.set(j, tPointPlus);
							scanLinePointSet.set(j + 1, tPoint);
						}
					}
				}
				za = scanLinePointSet.get(0);
				zb = scanLinePointSet.get(scanLinePointSet.size() - 1);
				zaXValue = za.getX2d();
				zbXValue = zb.getX2d();
				zaYValue = za.getY2d();
				zbYValue = zb.getY2d();				
				zaZValue = z1ZValue + ((Math.sqrt((zaXValue - z1XValue) * (zaXValue - z1XValue) + (zaYValue - z1YValue) * (zaYValue - z1YValue)))
						/ (Math.sqrt((z2XValue - z1XValue) * (z2XValue - z1XValue) + (z2YValue - z1YValue) * (z2YValue - z1YValue)))) * (z2ZValue - z1ZValue);
				zbZValue = z1ZValue + ((Math.sqrt((zbXValue - z1XValue) * (zbXValue - z1XValue) + (zbYValue - z1YValue) * (zbYValue - z1YValue)))
						/ (Math.sqrt((z3XValue - z1XValue) * (z3XValue - z1XValue) + (z3YValue - z1YValue) * (z3YValue - z1YValue)))) * (z3ZValue - z1ZValue);
				xMin = za.getX2d();
				xMax = zb.getX2d();
				for(int i = xMin; i <= xMax; i++) {
//					if(ys == 253 && i == 267) {
//						System.out.println("What?");
//					}
					zpZValue = zaZValue + ((Math.sqrt((i - zaXValue) * (i - zaXValue) + (ys - zaYValue) * (ys - zaYValue)))
							/ (Math.sqrt((zbXValue - zaXValue) * (zbXValue - zaXValue) + (zbYValue - zaYValue) * (zbYValue - zaYValue)))) * (zbZValue - zaZValue);
					if(zBuffer[i][ys].getZ() < zpZValue && zpZValue >= backFace && zpZValue <= frontFace) {
//					if(zBuffer[i][ys].getZ() < zpZValue) {
						zBuffer[i][ys].setZ(zpZValue);
						zBuffer[i][ys].setBeloPic(index);
					}
//					signPoint(i, ys, index);
				}
			}
		}
	}	
	
	public static void scanLineFill(List<DrawPoint> verSet, int index, double zminvalue) {
		int yMax = -2000;
		int yMin = 2000;
		List<DrawPoint> scanLinePointSet = new ArrayList<DrawPoint>();
		DrawPoint p1, p2, p3;
		p1 = verSet.get(0);
		p2 = verSet.get(1);
		p3 = verSet.get(2);		
		if(p1.getY2d() < yMin) {
			yMin = p1.getY2d();
		}
		if(p2.getY2d() < yMin) {
			yMin = p2.getY2d();
		}
		if(p3.getY2d() < yMin) {
			yMin = p3.getY2d();
		}
		if(p1.getY2d() > yMax) {
			yMax = p1.getY2d();
		}
		if(p2.getY2d() > yMax) {
			yMax = p2.getY2d();
		}
		if(p3.getY2d() > yMax) {
			yMax = p3.getY2d();
		}		
		for (int ys = yMin; ys <= yMax; ys++) {
			for (int i = 0; i < verSet.size() - 1; i++) {
				DrawPoint v1 = verSet.get(i);
				DrawPoint v2 = verSet.get(i + 1);
				int x1 = v1.getX2d();
				int y1 = v1.getY2d();
				double z1 = v1.getZ2d();
				int x2 = v2.getX2d();
				int y2 = v2.getY2d();
				double z2 = v2.getZ2d();
				if((y1 <= ys && y2 >= ys) || (y1 >= ys && y2 <= ys)) {
					//Line intersects with the scan line
					if(y1 == ys && y2 == ys) {
						//Horizontal line
						scanLinePointSet.add(v1);
						scanLinePointSet.add(v2);
					}
					else if(y1 == ys && y2 != ys && y1 < y2) {
						//EndPoints only count once
						scanLinePointSet.add(v1);
					}
					else if(y2 == ys && y1 != ys && y2 < y1) {
						//Same as above
						scanLinePointSet.add(v2);
					}
					else if(y1 != ys && y2 != ys) {
						//Points on the line between two end points
						double za = z1;						
						if(y2 != y1) {
							//Formula on slides
							za = z1 -(z1 - z2) * (y1 - ys) / (y1 - y2);
						}
						//Intersection points
						double xa = x1 + (ys - y1) * (x2 - x1) / (y2 - y1);
						int xInt = (int)(xa + 0.5);
						DrawPoint drawPoint = new DrawPoint(xInt, ys, za);
						scanLinePointSet.add(drawPoint);
					}	
				}	
			}
			for(int i = scanLinePointSet.size() - 1; i >= 0; i--) {
				for(int j = 0; j < i; j++) {
					DrawPoint tPoint = scanLinePointSet.get(j);
					DrawPoint tPointPlus = scanLinePointSet.get(j + 1);
					if(tPoint.getX2d() > tPointPlus.getX2d()) {
						scanLinePointSet.set(j, tPointPlus);
						scanLinePointSet.set(j + 1, tPoint);
					}
				}
			}
			if(!scanLinePointSet.isEmpty()) {
				for (int i = 0; i < scanLinePointSet.size() / 2; i++) {
					//Get the pair of closest intersection end points
					DrawPoint d1 = scanLinePointSet.get(2 * i);
					DrawPoint d2 = scanLinePointSet.get(2 * i + 1);
					int xMin = d1.getX2d();
					int xMax = d2.getX2d();
					double z1 = d1.getZ2d();
					double z2 = d2.getZ2d();
					for(int x = xMin; x <= xMax; x++) {
						double zp = z1;
						if(xMin != xMax) {
							//Formula given by slides
							zp = z2 - (double)((z2 - z1) * (xMax - x) / (xMax - xMin));
						}
						if(zp <= zminvalue && zp >= -1) {
							if(zp > (zBuffer[x][ys].getZ())) {
								zBuffer[x][ys].setZ(zp);
								zBuffer[x][ys].setBeloPic(index);
								int dec = (int)(20 / (1 + zminvalue) * (zp + 1));
								char c = getColorSym(dec, index);
								outputArray[x][ys] = c;
							}
//							else {
//								outputArray[x][ys] = '@';
//							}
						}
					}
				}
			}
			scanLinePointSet.clear();
		}		
	}
	
	public static char getColorSym(int dec, int index) {
		char c = '@';
		if(dec == 0) {
			if(index == 1) {
				c = '0';
			}
			else if(index == 2) {
				c = 'a';
			}
			else if(index == 3) {
				c = 'A';
			}
		}
		else if(dec == 1) {
			if(index == 1) {
				c = '1';
			}
			else if(index == 2) {
				c = 'b';
			}
			else if(index == 3) {
				c = 'B';
			}
		}
		else if(dec == 2) {
			if(index == 1) {
				c = '2';
			}
			else if(index == 2) {
				c = 'c';
			}
			else if(index == 3) {
				c = 'C';
			}
		}
		else if(dec == 3) {
			if(index == 1) {
				c = '3';
			}
			else if(index == 2) {
				c = 'd';
			}
			else if(index == 3) {
				c = 'D';
			}
		}
		else if(dec == 4) {
			if(index == 1) {
				c = '4';
			}
			else if(index == 2) {
				c = 'e';
			}
			else if(index == 3) {
				c = 'E';
			}
		}
		else if(dec == 5) {
			if(index == 1) {
				c = '5';
			}
			else if(index == 2) {
				c = 'f';
			}
			else if(index == 3) {
				c = 'F';
			}
		}
		else if(dec == 6) {
			if(index == 1) {
				c = '6';
			}
			else if(index == 2) {
				c = 'g';
			}
			else if(index == 3) {
				c = 'G';
			}
		}
		else if(dec == 7) {
			if(index == 1) {
				c = '7';
			}
			else if(index == 2) {
				c = 'h';
			}
			else if(index == 3) {
				c = 'H';
			}
		}
		else if(dec == 8) {
			if(index == 1) {
				c = '8';
			}
			else if(index == 2) {
				c = 'i';
			}
			else if(index == 3) {
				c = 'I';
			}
		}
		else if(dec == 9) {
			if(index == 1) {
				c = '9';
			}
			else if(index == 2) {
				c = 'j';
			}
			else if(index == 3) {
				c = 'J';
			}
		}
		else if(dec == 10) {
			if(index == 1) {
				c = '!';
			}
			else if(index == 2) {
				c = 'k';
			}
			else if(index == 3) {
				c = 'K';
			}
		}
		else if(dec == 11) {
			if(index == 1) {
				c = '#';
			}
			else if(index == 2) {
				c = 'l';
			}
			else if(index == 3) {
				c = 'L';
			}
		}
		else if(dec == 12) {
			if(index == 1) {
				c = '$';
			}
			else if(index == 2) {
				c = 'm';
			}
			else if(index == 3) {
				c = 'M';
			}
		}
		else if(dec == 13) {
			if(index == 1) {
				c = '%';
			}
			else if(index == 2) {
				c = 'n';
			}
			else if(index == 3) {
				c = 'N';
			}
		}
		else if(dec == 14) {
			if(index == 1) {
				c = '^';
			}
			else if(index == 2) {
				c = 'o';
			}
			else if(index == 3) {
				c = 'O';
			}
		}
		else if(dec == 15) {
			if(index == 1) {
				c = '&';
			}
			else if(index == 2) {
				c = 'p';
			}
			else if(index == 3) {
				c = 'P';
			}
		}
		else if(dec == 16) {
			if(index == 1) {
				c = '*';
			}
			else if(index == 2) {
				c = 'q';
			}
			else if(index == 3) {
				c = 'Q';
			}
		}
		else if(dec == 17) {
			if(index == 1) {
				c = '(';
			}
			else if(index == 2) {
				c = 'r';
			}
			else if(index == 3) {
				c = 'R';
			}
		}
		else if(dec == 18) {
			if(index == 1) {
				c = ')';
			}
			else if(index == 2) {
				c = 's';
			}
			else if(index == 3) {
				c = 'S';
			}
		}
		else if(dec == 19) {
			if(index == 1) {
				c = '_';
			}
			else if(index == 2) {
				c = 't';
			}
			else if(index == 3) {
				c = 'T';
			}
		}
		else {
			c = '@';
		}
		return c;
	}
	
	public static char pointColor(double zvalue, int index) {
		char c = '@';
		double color = 255 * (zvalue - backFace)/(frontFace - backFace);
		int limitedColor = (int) (color / 255.000001 * 20);
		if(limitedColor > 0 && limitedColor <= 13) {
			if(index == 1) {
				c = '0';
			}
			else if(index == 2) {
				c = 'a';
			}
			else if(index == 3) {
				c = 'A';
			}
		}
		else if(limitedColor <= 26) {
			if(index == 1) {
				c = '1';
			}
			else if(index == 2) {
				c = 'b';
			}
			else if(index == 3) {
				c = 'B';
			}
		}
		else if(limitedColor <= 39) {
			if(index == 1) {
				c = '2';
			}
			else if(index == 2) {
				c = 'c';
			}
			else if(index == 3) {
				c = 'C';
			}
		}
		else if(limitedColor <= 52) {
			if(index == 1) {
				c = '3';
			}
			else if(index == 2) {
				c = 'd';
			}
			else if(index == 3) {
				c = 'D';
			}
		}
		else if(limitedColor <= 65) {
			if(index == 1) {
				c = '4';
			}
			else if(index == 2) {
				c = 'e';
			}
			else if(index == 3) {
				c = 'E';
			}
		}
		else if(limitedColor <= 78) {
			if(index == 1) {
				c = '5';
			}
			else if(index == 2) {
				c = 'f';
			}
			else if(index == 3) {
				c = 'F';
			}
		}
		else if(limitedColor <= 91) {
			if(index == 1) {
				c = '6';
			}
			else if(index == 2) {
				c = 'g';
			}
			else if(index == 3) {
				c = 'G';
			}
		}
		else if(limitedColor <= 104) {
			if(index == 1) {
				c = '7';
			}
			else if(index == 2) {
				c = 'h';
			}
			else if(index == 3) {
				c = 'H';
			}
		}
		else if(limitedColor <= 117) {
			if(index == 1) {
				c = '8';
			}
			else if(index == 2) {
				c = 'i';
			}
			else if(index == 3) {
				c = 'I';
			}
		}
		else if(limitedColor <= 130) {
			if(index == 1) {
				c = '9';
			}
			else if(index == 2) {
				c = 'j';
			}
			else if(index == 3) {
				c = 'J';
			}
		}
		else if(limitedColor <= 143) {
			if(index == 1) {
				c = '!';
			}
			else if(index == 2) {
				c = 'k';
			}
			else if(index == 3) {
				c = 'K';
			}
		}
		else if(limitedColor <= 156) {
			if(index == 1) {
				c = '#';
			}
			else if(index == 2) {
				c = 'l';
			}
			else if(index == 3) {
				c = 'L';
			}
		}
		else if(limitedColor <= 169) {
			if(index == 1) {
				c = '$';
			}
			else if(index == 2) {
				c = 'm';
			}
			else if(index == 3) {
				c = 'M';
			}
		}
		else if(limitedColor <= 182) {
			if(index == 1) {
				c = '%';
			}
			else if(index == 2) {
				c = 'n';
			}
			else if(index == 3) {
				c = 'N';
			}
		}
		else if(limitedColor <= 195) {
			if(index == 1) {
				c = '^';
			}
			else if(index == 2) {
				c = 'o';
			}
			else if(index == 3) {
				c = 'O';
			}
		}
		else if(limitedColor <= 208) {
			if(index == 1) {
				c = '&';
			}
			else if(index == 2) {
				c = 'p';
			}
			else if(index == 3) {
				c = 'P';
			}
		}
		else if(limitedColor <= 221) {
			if(index == 1) {
				c = '*';
			}
			else if(index == 2) {
				c = 'q';
			}
			else if(index == 3) {
				c = 'Q';
			}
		}
		else if(limitedColor <= 234) {
			if(index == 1) {
				c = '(';
			}
			else if(index == 2) {
				c = 'r';
			}
			else if(index == 3) {
				c = 'R';
			}
		}
		else if(limitedColor <= 247) {
			if(index == 1) {
				c = ')';
			}
			else if(index == 2) {
				c = 's';
			}
			else if(index == 3) {
				c = 'S';
			}
		}
		else if(limitedColor <= 255) {
			if(index == 1) {
				c = '_';
			}
			else if(index == 2) {
				c = 't';
			}
			else if(index == 3) {
				c = 'T';
			}
		}
		else {
			c = '@';
		}
		return c;
	}

}
