package Lab5;

public class DummyMatrix extends Matrix {

	public DummyMatrix(int _rows, int _cols, int[] args) {
		super(_rows, _cols, args);
	}

	public Matrix add(Matrix other) throws MatrixDimensionsException {
		if (this.rows != other.rows || this.cols != other.cols) {
			Matrix result = new Matrix(this.rows, this.cols);
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.cols; j++) {
					if (i < other.rows && j < other.cols)
						result.table[i][j] = this.table[i][j]
								+ other.table[i][j];
					else
						result.table[i][j] = 1;
				}
			}
			throw new MatrixDimensionsException(result);
		}
		Matrix result = new Matrix(other.rows, other.cols);
		for (int i = 0; i < other.rows; i++) {
			for (int j = 0; j < other.cols; j++) {
				result.table[i][j] = this.table[i][j] + other.table[i][j];
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
}
