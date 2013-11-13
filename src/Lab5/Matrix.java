package Lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Matrix {
	public int rows;
	public int cols;
	public int[][] table;

	public Matrix(String file) throws IOException {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(file));
			rows = Integer.parseInt(bf.readLine());
			cols = Integer.parseInt(bf.readLine());
			table = new int[rows][cols];
			for (int i = 0; i < rows; i++) {
				String[] line = bf.readLine().split(" ");
				for (int j = 0; j < cols; j++) {
					table[i][j] = Integer.parseInt(line[j]);
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (bf != null)
				bf.close();
		}
	}

	public Matrix(int _rows, int _cols, int... args) {
		rows = _rows;
		cols = _cols;
		table = new int[rows][cols];
		for (int i = 0; i < _rows; i++) {
			for (int j = 0; j < _cols; j++) {
				table[i][j] = args[i * _rows + j];
			}
		}
	}

	private Matrix(int _rows, int _cols) {
		rows = _rows;
		cols = _cols;
		table = new int[_rows][_cols];
	}

	public Matrix add(Matrix other) throws MatrixDimensionsException {
		if (this.rows != other.rows || this.cols != other.cols)
			throw new MatrixDimensionsException();
		Matrix result = new Matrix(other.rows, other.cols);
		for (int i = 0; i < other.rows; i++) {
			for (int j = 0; j < other.cols; j++) {
				result.table[i][j] = this.table[i][j] + other.table[i][j];
			}
		}
		return result;
	}

	public Matrix sub(Matrix other) throws MatrixDimensionsException {
		if (this.rows != other.rows || this.cols != other.cols)
			throw new MatrixDimensionsException();
		Matrix result = new Matrix(other.rows, other.cols);
		for (int i = 0; i < other.rows; i++) {
			for (int j = 0; j < other.cols; j++) {
				result.table[i][j] = this.table[i][j] - other.table[i][j];
			}
		}
		return result;
	}

	public Matrix mul(Matrix other) throws MatrixDimensionsException {
		if (this.cols != other.rows)
			throw new MatrixDimensionsException();
		Matrix result = new Matrix(other.rows, this.cols);
		for (int i = 0; i < other.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				for (int k = 0; k < this.rows; k++) {
					result.table[i][j] += this.table[i][k] * other.table[k][j];
				}
			}
		}
		return result;
	}

	public void print() {
		for (int i = 0; i < rows; i++) {
			System.out.println();
			for (int j = 0; j < cols; j++) {
				System.out.print(table[i][j]);
				System.out.print(" ");
			}
		}
	}

	public static void main(String[] args) {
		try {
			Matrix one = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
			Matrix two = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
			Matrix three = new Matrix("macierz.txt");
			Matrix result = new Matrix(3, 3);
			result = one.add(two);
			result.print();
			result = one.sub(two);
			result.print();
			result = one.mul(two);
			result.print();
			result = one.add(three);
		} catch (MatrixDimensionsException e) {
			System.out.println("\nWrong matrix dimensions!");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
