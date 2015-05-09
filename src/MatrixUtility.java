/**
* @Title Homework 5
* @student Zhichao Cao
* @email zc77@drexel.edu
* 
*/

//Class for Matrix operation
public class MatrixUtility {
	private int rows; // number of rows
	private int cols; // number of columns
	private double[][] cell; // array
	
	public MatrixUtility(int m, int n) {
		this.rows = m;
		this.cols = n;
		this.cell = new double[m][n];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.cell[i][j] = 0;
			}
		}
	}
	
	public MatrixUtility(double[][] cell) {
		rows = cell.length;
		cols = cell[0].length;
		this.cell = new double[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.cell[i][j] = cell[i][j];
			}
		}
	}
	
	public double getCell(int x, int y) {
		return this.cell[x][y];
	}
	
	public void setCell(int x, int y, double value) {
		this.cell[x][y] = value;
	}
	
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
	
	public MatrixUtility multiplication(MatrixUtility bMatrix) {
		MatrixUtility cMatrix = new MatrixUtility(this.rows, bMatrix.cols);
		if (this.cols != bMatrix.rows) {
			return null;
		}
		else {
			for (int i = 0; i < cMatrix.rows; i++) {
				for (int j = 0; j < cMatrix.cols; j++) {
					for (int k = 0; k < this.cols; k++) {
						cMatrix.cell[i][j] += this.cell[i][k] * bMatrix.cell[k][j];
					}
				}
			}
		}
		return cMatrix;
	}
	
	public MatrixUtility equalSet() {
		MatrixUtility tarMatrix = new MatrixUtility(this.rows, this.cols);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				tarMatrix.setCell(i, j, this.getCell(i, j));
			}
		}
		return tarMatrix;
	}
	
	public double cullModel() {
		double v = 0.0;
		if(this.rows == 3 && this.cols == 3) {
			double a00 = this.getCell(0, 0);
			double a01 = this.getCell(0, 1);
			double a02 = this.getCell(0, 2);
			double a10 = this.getCell(1, 0);
			double a11 = this.getCell(1, 1);
			double a12 = this.getCell(1, 2);
			double a20 = this.getCell(2, 0);
			double a21 = this.getCell(2, 1);
			double a22 = this.getCell(2, 2);
			v = a00 * a11 * a22 + a01 * a12 * a20 + a02 * a10 * a21 - a02 * a11 * a20 - a01 * a10 * a22 - a00 * a12 * a21;
		}
		return v;
	}
	
}
