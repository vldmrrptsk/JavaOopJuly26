package ru.academits.repetskiy.matrix;

import ru.academits.repetskiy.vector.Vector;

public class Matrix {
    private int columnsLength;
    private int rowsLength;
    public Vector[] matrixElements;

    public Matrix(int rowsLength, int columnsLength) {
        this.columnsLength = columnsLength;
        this.rowsLength = rowsLength;

        matrixElements = new Vector[rowsLength];

        for (int i = 0; i < rowsLength; i++) {
            matrixElements[i] = new Vector(columnsLength);
        }
    }

    public Matrix(double[][] matrix) {
        rowsLength = matrix.length;
        columnsLength = matrix[0].length;

        matrixElements = new Vector[rowsLength];

        for (int i = 0; i < rowsLength; i++) {
            matrixElements[i] = new Vector(matrix[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this.rowsLength = matrix.rowsLength;
        this.columnsLength = matrix.columnsLength;

        matrixElements = new Vector[matrix.rowsLength];

        for (int i = 0; i < matrix.rowsLength; i++) {
            matrixElements[i] = new Vector(matrix.matrixElements[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        this.rowsLength = vectors.length;
        this.columnsLength = vectors[0].getSize();
        matrixElements = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            matrixElements[i] = new Vector(vectors[i]);
        }
    }

    public int[] shape() {
        return new int[]{rowsLength, columnsLength};
    }

    public Vector getRow(int index) {
        return matrixElements[index];
    }

    public void setRow(int index, Vector vector) {
        if (this.rowsLength < index || index < 0) {
            throw new IllegalArgumentException("Индекс выходит за границы строк!: {Количество строк: }" + this.rowsLength);
        }

        matrixElements[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        double[] coordinate = new double[rowsLength];

        for (int i = 0; i < rowsLength; i++) {
            coordinate[i] = matrixElements[i].getCoordinate(index);
        }

        return new Vector(rowsLength, coordinate);
    }

    public void transpose() {
        int temp = rowsLength;
        rowsLength = columnsLength;
        columnsLength = temp;

        double[] temporaryArray = new double[columnsLength];

        Matrix matrixTemp = new Matrix(rowsLength, columnsLength);

        for (int i = 0; i < rowsLength; i++) {
            for (int j = 0; j < columnsLength; j++) {
                temporaryArray[j] = this.matrixElements[j].getCoordinate(i);
            }
            matrixTemp.matrixElements[i] = new Vector(temporaryArray);
        }

        this.matrixElements = matrixTemp.matrixElements;

    }

    public void multiplyMatrixByScalar(int scalar) {
        for (int i = 0; i < rowsLength; i++) {
            matrixElements[i].multiplyVectorByScalar(scalar);
        }
    }

    public void add(Matrix array2D) {
        for (int i = 0; i < rowsLength; i++) {
            this.matrixElements[i].getAdd(array2D.getRow(i));
        }
    }

    public void subtract(Matrix array2D) {
        for (int i = 0; i < rowsLength; i++) {
            this.matrixElements[i].getSubtract(array2D.getRow(i));
        }
    }

    private static void swapRows(Vector[] matrixElements, int row1, int row2) {
        Vector tepVector = matrixElements[row1];
        matrixElements[row1] = matrixElements[row2];
        matrixElements[row2] = tepVector;

    }

    public double determinant() {
        final double EPSILON = 1e-10;
        int copyRowsLengthMatrix = this.rowsLength;
        Vector[] copyMatrixElements = new Vector[copyRowsLengthMatrix];

        for (int i = 0; i < copyRowsLengthMatrix; i++) {
            copyMatrixElements[i] = new Vector(this.matrixElements[i]);
        }

        double det = 1.0;

        for (int i = 0; i < copyRowsLengthMatrix; i++) {
            int maxRow = i;
            double maxValue = Math.abs(copyMatrixElements[i].getCoordinate(i));

            for (int k = i + 1; k < copyRowsLengthMatrix; k++) {
                double currentValue = Math.abs(copyMatrixElements[k].getCoordinate(i));
                if (currentValue > maxValue) {
                    maxValue = currentValue;
                    maxRow = k;
                }
            }

            if (maxValue < EPSILON) {
                return 0.0;
            }

            if (maxRow != i) {
                swapRows(copyMatrixElements, i, maxRow);
                det = -det;
            }

            for (int j = i + 1; j < copyRowsLengthMatrix; j++) {
                double factor = copyMatrixElements[j].getCoordinate(i) / copyMatrixElements[i].getCoordinate(i);

                for (int k = i; k < copyRowsLengthMatrix; k++) {
                    double newValue = copyMatrixElements[j].getCoordinate(k)
                            - factor * copyMatrixElements[i].getCoordinate(k);

                    copyMatrixElements[j].setCoordinate(k, newValue);
                }
            }
        }

        for (int i = 0; i < copyRowsLengthMatrix; i++) {
            det = det * copyMatrixElements[i].getCoordinate(i);
        }

        return det;
    }

    public Vector getDot(Vector vector) {
        if (columnsLength != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора не совпадает с размером столбцов в матрице");
        }

        double[] vectorCoordinates = new double[rowsLength];
        double coordinate = 0.0;

        for (int i = 0; i < rowsLength; i++) {
            for (int j = 0; j < columnsLength; j++) {
                coordinate += matrixElements[i].getCoordinate(j) * vector.getCoordinate(j);
            }
            vectorCoordinates[i] = coordinate;
            coordinate = 0;
        }

        return new Vector(rowsLength, vectorCoordinates);
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.add(matrix2);

        return matrix1;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.subtract(matrix2);

        return matrix1;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int row = matrix1.rowsLength;
        int column = matrix1.columnsLength;
        double[][] matrix = new double[row][column];
        double element = 0.0;

        for (int k = 0; k < row; k++) {
            for (int i = 0; i < column; i++) {
                for (int j = 0; j < column; j++) {
                    element += matrix1.matrixElements[k].getCoordinate(j) * matrix2.getColumn(i).getCoordinate(j);
                }
                matrix[k][i] = element;
                element = 0.0;
            }
        }

        return new Matrix(matrix);
    }

    @Override
    public String toString() {
        int rows = this.shape()[0];
        int columns = this.shape()[1];
        String[][] stringCoordinates = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                stringCoordinates[i][j] = String.valueOf(matrixElements[i].getCoordinate(j));
            }
        }

        StringBuilder stringOutput = new StringBuilder();
        stringOutput.append("{");
        for (int i = 0; i < rows; i++) {
            stringOutput.append("{");
            stringOutput.append(String.join(", ", stringCoordinates[i]));
            stringOutput.append("}");
            if (i < rows - 1) {
                stringOutput.append(", ");
            }
        }
        stringOutput.append("}");

        return stringOutput.toString();
    }
}
