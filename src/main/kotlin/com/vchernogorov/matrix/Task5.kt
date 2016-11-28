package com.vchernogorov.matrix

import java.util.*

// Gaussian elimination
class Task5 (val A: DoubleMatrix, val B: DoubleArray) : Task<DoubleArray>(DoubleArray(B.size)) {

    val size = A.rows.size
    val P = DoubleArray(size + 1)
    val Q = DoubleArray(size + 1)

    override fun run() {
        echelonForm()
        inverseEchelonForm()
    }

    fun echelonForm() {
        var countZero = 0
        val numbers = ArrayList<Int>()
        for (i in A.rows.indices) {
            var count = 1
            while (A[i][i] == 0.0) {
                if (count >= A.rows.size) {
                    break
                }
                countZero++
                numbers.add(i)
                A.swapRows(B, i, i + count++)
            }
            B[i] /= A[i][i]

            if (A[i][i] != 1.0) {
                val divisor = A[i][i]
                for (j in A[i].indices) {
                    A[i][j] /= divisor
                }
            }

            for (j in i + 1..A.rows.size - 1) {
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
            val index = A.rows.size - 1 - i
            result[index] = B[index]

            for (j in index + 1..A.rows.size - 1) {
                result[index] -= result[j] * A[index][j]
            }
        }
    }
}
