package com.vchernogorov.matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        double[][] matrix = new double[][] { { 10, 1, 0 }, { 1, 20, 2 }, { 0, 3, 30 } };
        double[] vector = new double[] { 11, 23, 33 };
        double[] answer = new double[vector.length];

        System.out.println("  Initial matrix");
        printMatrixWithVector(matrix, vector);

        goForwardStroke(matrix, vector);

        System.out.println("  Matrix after forward stroke");
        printMatrixWithVector(matrix, vector);

        goReverseMotion(matrix, vector, answer);

        System.out.print("  Answer is ");
        printLine(answer);
    }

    public static void goForwardStroke(double[][] matrix, double[] vector) {
        int countZero = 0;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int count = 1;
            while (matrix[i][i] == 0) {
                if (count >= matrix.length) {
                    break;
                }
                countZero++;
                numbers.add(i);
                swapLines(matrix, vector, i, i + count++);
            }
            vector[i] /= matrix[i][i];

            if (matrix[i][i] != 1) {
                divideAtElement(matrix[i], matrix[i][i]);
            }

            for (int j = i + 1; j < matrix.length; j++) {
                double subtract = matrix[j][i] / matrix[i][i];
                if (matrix[i][j] - subtract != matrix[i][j]) {
                    subtractLines(matrix[i], matrix[j], subtract);
                    vector[j] -= vector[i] * subtract;
                }
            }
        }
        System.out.println("  Количество нулей на главной диагонали - " + countZero + "\n  Нули встретились на ");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("      номере диагонали [" + (numbers.get(i) + 1) + ", " + (numbers.get(i) + 1) + "]");
        }
        System.out.println();
    }

    public static void divideAtElement(double[] line, double value) {
        for (int i = 0; i < line.length; i++) {
            line[i] /= value;
        }
    }

    public static void subtractLines(double[] firstLine, double[] secondLine, double substractElem) {
        for (int i = 0; i < firstLine.length; i++) {
            secondLine[i] -= firstLine[i] * substractElem;
        }
    }

    public static void swapLines(double[][] matrix, double[] vector, int indexLine, int indexToSwapLine) {
        double[] tempLine = matrix[indexLine];
        double tempVector = vector[indexLine];

        double element = matrix[indexLine][indexLine];
        int swapIndex = indexToSwapLine;
        while (indexToSwapLine < vector.length) {
            if (element < Math.abs(matrix[indexLine][indexToSwapLine])) {
                element = Math.abs(matrix[indexLine][indexToSwapLine]);
                swapIndex = indexToSwapLine;
            }
            indexToSwapLine++;
        }

        matrix[indexLine] = matrix[swapIndex];
        vector[indexLine] = vector[swapIndex];

        matrix[swapIndex] = tempLine;
        vector[swapIndex] = tempVector;
    }

    public static void goReverseMotion(double[][] matrix, double[] vector, double[] answer) {
        for (int i = 0; i < matrix.length; i++) {
            int index = matrix.length - 1 - i;
            answer[index] = vector[index];

            for (int j = index + 1; j < matrix.length; j++) {
                answer[index] -= answer[j] * matrix[index][j];
            }
        }
    }

    public static double[][] readMatrixFromFile(String path) {
        double[][] matrix;
        try (BufferedReader fileStream = new BufferedReader(new FileReader(path))) {
            int dimension = Integer.parseInt(fileStream.readLine());
            matrix = new double[dimension][dimension];

            for (int i = 0; i < dimension; i++) {
                String[] numbers = fileStream.readLine().split(" ");
                for (int j = 0; j < dimension; j++) {
                    Double x = 5.5;
                    matrix[i][j] = Double.parseDouble(numbers[j]);
                }
            }
        } catch (IOException exception) {
            System.err.println("Reading is break");
            matrix = new double[0][0];
        }
        return matrix;
    }

    public static double[] readVectorFromFile(String path) {
        double[] vector;

        try (BufferedReader fileStream = new BufferedReader(new FileReader(path))) {
            int dimension = Integer.parseInt(fileStream.readLine());
            vector = new double[dimension];
            String[] numbers = fileStream.readLine().split(" ");

            for (int i = 0; i < dimension; i++) {
                vector[i] = Double.parseDouble(numbers[i]);
            }

        } catch (IOException exception) {
            System.err.println("Reading is break");
            vector = new double[0];
        }
        return vector;
    }

    public static void printMatrixWithVector(double[][] matrix, double[] vector) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == -0) {
                    matrix[i][j] = 0;
                }
                System.out.print(String.format("%(6.2f ", matrix[i][j]));
            }
            System.out.println(String.format(String.format("\t\t\t%(.2f", vector[i])));
        }
        System.out.println();
    }

    public static void printLine(double[] line) {
        for (int i = 0; i < line.length; i++) {
            System.out.print(String.format("%(.4f\t", line[i]));
        }

        System.out.println();
    }
}
