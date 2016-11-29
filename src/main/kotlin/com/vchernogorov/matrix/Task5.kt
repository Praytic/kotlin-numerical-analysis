package com.vchernogorov.matrix

import com.vchernogorov.Task

// Gaussian elimination
class Task5 (val A: DoubleMatrix, val B: DoubleArray) : Task<DoubleArray>(DoubleArray(B.size)) {

    val size = A.rows.size

    override fun run() {
        echelonForm()
        inverseEchelonForm()
    }

    fun echelonForm() {
        for (i in A.rows.indices) {
            while (A[i][i] == 0.0) {
                if (i + 1 >= size) {
                    break
                }
                A.swapRows(B, i, i + 1)
            }
            B[i] /= A[i][i]

            if (A[i][i] != 1.0) {
                val divisor = A[i][i]
                for (j in A[i].indices) {
                    A[i][j] /= divisor
                }
            }

            for (j in i + 1..size - 1) {
                val subtract = A[j][i] / A[i][i]
                if (A[i][j] - subtract != A[i][j]) {
                    for (k in A[i].indices) {
                        A[j][k] -= A[i][k] * subtract
                    }
                    B[j] -= B[i] * subtract
                }
            }
        }
    }

    fun inverseEchelonForm() {
        for (i in A.rows.indices) {
            val index = size - 1 - i
            result[index] = B[index]

            for (j in index + 1..size - 1) {
                result[index] -= result[j] * A[index][j]
            }
        }
    }
}
