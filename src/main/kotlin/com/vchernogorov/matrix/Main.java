package com.vchernogorov.matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        double[][] matrix = new double[][] { { 3, 2, 0 }, { 4, 2, 8 }, { 0, 5, 1 } };
        double[] vector = new double[] { 7, 9, 9 };
        final double E = 0.000001;

        System.out.println("Считанная матрица с вектором:");
        printMatrixWithVector(matrix, vector);

        double[][] alphaMatrix = fillAlphaMatrix(matrix);

        double[] denominatorArray = fillDenominatorArray(matrix);
        double[] betaVector = fillBetaVector(vector, denominatorArray);

        System.out.println("Новая считанная матрица с вектором:");
        printMatrixWithVector(alphaMatrix, betaVector);

        Double[] answer = iteration(alphaMatrix, betaVector, E);
        System.out.println("\nФинальный ответ");
        printVector(answer);
    }

    public static Double[] iteration(double[][] matrix, double[] vector, double e) {
        List<Double[]> xElements = new ArrayList<>();
        Double[] steepVector = new Double[vector.length];

        for (int i = 0; i < vector.length; i++) {
            steepVector[i] = vector[i];
        }
        int count = 0;

        xElements.add(steepVector);

        printVector(xElements.get(xElements.size() - 1), ++count);

        steepVector = multiplyMatrixWithVector(matrix, xElements.get(xElements.size() - 1));
        steepVector = addVectors(steepVector, vector);
        xElements.add(steepVector);

        printVector(xElements.get(xElements.size() - 1), ++count);

        while (vectorsNorm(xElements.get(xElements.size() - 2), xElements.get(xElements.size() - 1)) > e) {
            steepVector = multiplyMatrixWithVector(matrix, xElements.get(xElements.size() - 1));
            steepVector = addVectors(steepVector, vector);
            xElements.add(steepVector);
            printVector(xElements.get(xElements.size() - 1), ++count);
        }

        return xElements.get(xElements.size() - 1);
    }

    public static double[][] fillAlphaMatrix(double[][] matrix) {
        double[][] alphaMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    alphaMatrix[i][j] = 0;
                } else {
                    alphaMatrix[i][j] = -matrix[i][j] / matrix[i][i];
                }
            }
        }

        return alphaMatrix;
    }

    public static double[] fillDenominatorArray(double[][] matrix) {
        double[] denominatorArray = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            denominatorArray[i] = matrix[i][i];
        }
        return denominatorArray;
    }

    public static double[] fillBetaVector(double[] vector, double[] denominatorArray) {
        double[] betaVector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            betaVector[i] = vector[i] / denominatorArray[i];
        }
        return betaVector;
    }

    public static Double[] multiplyMatrixWithVector(double[][] matrix, Double[] vector) {
        Double[] resultVector = new Double[vector.length];
        Arrays.fill(resultVector, 0.0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                resultVector[i] += matrix[i][j] * vector[j];
            }
        }

        return resultVector;
    }

    public static Double[] addVectors(Double[] firstVector, double[] secondVector) {
        Double[] resultVector = new Double[firstVector.length];

        for (int i = 0; i < resultVector.length; i++) {
            resultVector[i] = firstVector[i] + secondVector[i];
        }

        return resultVector;
    }

    public static double vectorsNorm(Double[] firstVector, Double[] secondVector) {
        double maxNorm = -1;

        for (int i = 0; i < firstVector.length; i++) {
            if (Math.abs(secondVector[i] - firstVector[i]) > maxNorm) {
                maxNorm = Math.abs(secondVector[i] - firstVector[i]);
            }
        }

        return maxNorm;
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

    public static void printVector(Double[] line, int count) {
        for (int i = 0; i < line.length; i++) {
            System.out.print(String.format("%(.4f\t", line[i]));
        }

        System.out.println(" шаг №" + count);
    }

    public static void printVector(Double[] line) {
        for (int i = 0; i < line.length; i++) {
            System.out.print(String.format("%(.4f\t", line[i]));
        }

        System.out.println();
    }
}
