package com.vchernogorov.matrix

import com.vchernogorov.Task
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*

class Task7 (val matrix: DoubleMatrix, val vector: DoubleArray) : Task<DoubleArray>(doubleArrayOf()) {

    val E = 0.000001

    override fun run() {

        println("Считанная матрица с вектором:")
        printMatrixWithVector(matrix, vector)

        val alphaMatrix = fillAlphaMatrix(matrix)

        val denominatorArray = fillDenominatorArray(matrix)
        val betaVector = fillBetaVector(vector, denominatorArray)

        println("Новая считанная матрица с вектором:")
        printMatrixWithVector(alphaMatrix, betaVector)

        val answer = iteration(alphaMatrix, betaVector, E)
        println("\nФинальный ответ")
        printVector(answer)
    }

    fun iteration(matrix: DoubleMatrix, vector: DoubleArray, e: Double): DoubleArray {
        val xElements = ArrayList<DoubleArray>()
        var steepVector = doubleArrayOf(*vector)
        var count = 0

        xElements.add(steepVector)

        printVector(xElements.get(xElements.size - 1), ++count)

        steepVector = multiplyMatrixWithVector(matrix, xElements.get(xElements.size - 1))
        steepVector = addVectors(steepVector, vector)
        xElements.add(steepVector)

        printVector(xElements.get(xElements.size - 1), ++count)

        while (vectorsNorm(xElements.get(xElements.size - 2), xElements.get(xElements.size - 1)) > e) {
            steepVector = multiplyMatrixWithVector(matrix, xElements.get(xElements.size - 1))
            steepVector = addVectors(steepVector, vector)
            xElements.add(steepVector)
            printVector(xElements.get(xElements.size - 1), ++count)
        }

        return xElements.get(xElements.size - 1)
    }

    fun fillAlphaMatrix(matrix: DoubleMatrix): DoubleMatrix {
        val alphaMatrix = DoubleMatrix(matrix.rows.size)
        for (i in matrix.rows.indices) {
            for (j in matrix.rows.indices) {
                if (i == j) {
                    alphaMatrix[i][j] = 0.0
                } else {
                    alphaMatrix[i][j] = -matrix[i][j] / matrix[i][i]
                }
            }
        }
        return alphaMatrix
    }

    fun fillDenominatorArray(matrix: DoubleMatrix): DoubleArray {
        val denominatorArray = DoubleArray(matrix.rows.size)
        for (i in matrix.rows.indices) {
            denominatorArray[i] = matrix[i][i]
        }
        return denominatorArray
    }

    fun fillBetaVector(vector: DoubleArray, denominatorArray: DoubleArray): DoubleArray {
        val betaVector = DoubleArray(vector.size)
        for (i in vector.indices) {
            betaVector[i] = vector[i] / denominatorArray[i]
        }
        return betaVector
    }

    fun multiplyMatrixWithVector(matrix: DoubleMatrix, vector: DoubleArray): DoubleArray {
        val resultVector = DoubleArray(vector.size)
        for (i in matrix.rows.indices) {
            for (j in vector.indices) {
                resultVector[i] += matrix[i][j] * vector[j]
            }
        }
        return resultVector
    }

    fun addVectors(firstVector: DoubleArray, secondVector: DoubleArray): DoubleArray {
        val resultVector = DoubleArray(firstVector.size)
        for (i in resultVector.indices) {
            resultVector[i] = firstVector[i] + secondVector[i]
        }
        return resultVector
    }

    fun vectorsNorm(firstVector: DoubleArray, secondVector: DoubleArray): Double {
        var maxNorm = -1.0

        for (i in firstVector.indices) {
            if (Math.abs(secondVector[i] - firstVector[i]) > maxNorm) {
                maxNorm = Math.abs(secondVector[i] - firstVector[i])
            }
        }

        return maxNorm
    }

    fun readMatrixFromFile(path: String): DoubleMatrix {
        var matrix = DoubleMatrix()
        try {
            BufferedReader(FileReader(path)).use({ fileStream ->
                val dimension = Integer.parseInt(fileStream.readLine())
                matrix = DoubleMatrix(dimension)

                for (i in 0..dimension - 1) {
                    val numbers = fileStream.readLine().split((" ").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    for (j in 0..dimension - 1) {
                        matrix[i][j] = java.lang.Double.parseDouble(numbers[j])
                    }
                }
            })
        } catch (exception: IOException) {
            System.err.println("Reading is break")
            matrix = DoubleMatrix()
        }
        return matrix
    }

    fun readVectorFromFile(path: String): DoubleArray {
        var vector: DoubleArray = doubleArrayOf()
        try {
            BufferedReader(FileReader(path)).use({ fileStream ->
                val dimension = Integer.parseInt(fileStream.readLine())
                vector = DoubleArray(dimension)
                val numbers = fileStream.readLine().split((" ").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

                for (i in 0..dimension - 1) {
                    vector[i] = java.lang.Double.parseDouble(numbers[i])
                }

            })
        } catch (exception: IOException) {
            System.err.println("Reading is break")
            vector = DoubleArray(0)
        }

        return vector
    }

    fun printMatrixWithVector(matrix: DoubleMatrix, vector: DoubleArray) {
        for (i in matrix.rows.indices) {
            for (j in matrix.rows.indices) {
                if (matrix[i][j] == -0.0) {
                    matrix[i][j] = 0.0
                }
                print(String.format("%(6.2f ", matrix[i][j]))
            }
            println(String.format(String.format("\t\t\t%(.2f", vector[i])))
        }
        println()
    }

    fun printVector(line: DoubleArray, count: Int) {
        for (i in line.indices) {
            print(String.format("%(.4f\t", line[i]))
        }

        println(" шаг №" + count)
    }

    fun printVector(line: DoubleArray) {
        for (i in line.indices) {
            print(String.format("%(.4f\t", line[i]))
        }

        println()
    }
}
