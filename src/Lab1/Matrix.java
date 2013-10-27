package Lab1;

public class Matrix {
	public int rows;
	public int cols;
	public int[][] table;

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

	public Matrix add(Matrix other) {
		Matrix result = new Matrix(other.rows, other.cols);
		for (int i = 0; i < other.rows; i++) {
			for (int j = 0; j < other.cols; j++) {
				result.table[i][j] = this.table[i][j] + other.table[i][j];
			}
		}
		return result;
	}

	public Matrix sub(Matrix other) {
		Matrix result = new Matrix(other.rows, other.cols);
		for (int i = 0; i < other.rows; i++) {
			for (int j = 0; j < other.cols; j++) {
				result.table[i][j] = this.table[i][j] - other.table[i][j];
			}
		}
		return result;
	}

	public Matrix mul(Matrix other) {
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
		Matrix one = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix two = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix result = new Matrix(3, 3);
		result = one.add(two);
		result.print();
		result = one.sub(two);
		result.print();
		result = one.mul(two);
		result.print();
	}

}
