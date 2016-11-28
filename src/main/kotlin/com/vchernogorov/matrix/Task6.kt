package com.vchernogorov.matrix

import java.io.BufferedReader
import java.io.FileReader

class Task6 (matrix: DoubleMatrix, vector: DoubleArray) : Task<DoubleArray>(doubleArrayOf()) {

    override fun run() {
        val matrix = readMatrixFromFile("D:\\Java\\sweep method\\src\\com\\company\\matrix.txt")
        val vector = readVectorFromFile("D:\\Java\\sweep method\\src\\com\\company\\vector.txt")
        val answer = DoubleArray(vector.size)

        println("Считанная матрица с вектором:")
        printMatrixWithVector(matrix, vector)

        val arrayP = DoubleArray(matrix.rows.size + 1)
        val arrayQ = DoubleArray(matrix.rows.size + 1)

        goForwardStroke(matrix, vector, arrayP, arrayQ)

        printArrayQandP(arrayP, arrayQ)

        goReverseMotion(arrayP, arrayQ, answer)

        println("Ответ")
        printLine(answer)
    }

    fun goForwardStroke(matrix: DoubleMatrix, vector: DoubleArray, arrayP: DoubleArray, arrayQ: DoubleArray) {
        arrayP[1] = matrix[0][1] / -matrix[0][0]
        arrayQ[1] = -vector[0] / -matrix[0][0]

        for (i in 1..vector.size - 1) {
            if (i != vector.size - 1) {
                arrayP[i + 1] = matrix[i][i + 1] / (-matrix[i][i] - matrix[i][i - 1] * arrayP[i])
            } else {
                arrayP[i + 1] = 0.0
            }

            arrayQ[i + 1] = (matrix[i][i - 1] * arrayQ[i] - vector[i]) / (-matrix[i][i] - matrix[i][i - 1] * arrayP[i])
        }
    }

    fun goReverseMotion(arrayP: DoubleArray, arrayQ: DoubleArray, answer: DoubleArray) {
        answer[answer.size - 1] = arrayQ[answer.size]
        for (i in answer.size - 2 downTo 0) {
            answer[i] = arrayP[i + 1] * answer[i + 1] + arrayQ[i + 1]
        }
    }

    fun readMatrixFromFile(path: String): DoubleMatrix {
        var matrix = DoubleMatrix()
        BufferedReader(FileReader(path)).use { fileStream ->
            val dimension = Integer.parseInt(fileStream.readLine())
            matrix = DoubleMatrix(dimension)

            for (i in 0..dimension - 1) {
                val numbers = fileStream.readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (j in 0..dimension - 1) {
                    val x = 5.5
                    matrix[i][j] = java.lang.Double.parseDouble(numbers[j])
                }
            }
        }

        return matrix
    }

    fun readVectorFromFile(path: String): DoubleArray {
        var vector = doubleArrayOf()

        BufferedReader(FileReader(path)).use { fileStream ->
            val dimension = Integer.parseInt(fileStream.readLine())
            vector = DoubleArray(dimension)
            val numbers = fileStream.readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            for (i in 0..dimension - 1) {
                vector[i] = java.lang.Double.parseDouble(numbers[i])
            }
        }

        return vector
    }

    fun printArrayQandP(arrayP: DoubleArray, arrayQ: DoubleArray) {
        println("Массив P")
        for (i in 1..arrayP.size - 1) {
            print(String.format("%.4f\t", arrayP[i]))
        }

        println("\nМассив Q")
        for (i in 1..arrayQ.size - 1) {
            print(String.format("%.4f\t", arrayQ[i]))
        }
        println()
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

    fun printLine(line: DoubleArray) {
        for (i in line.indices) {
            print(String.format("%(.4f\t", line[i]))
        }

        println()
    }
}
