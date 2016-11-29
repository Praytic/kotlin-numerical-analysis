package com.vchernogorov.matrix

import com.vchernogorov.Task

class Task6 (val A: DoubleMatrix, val B: DoubleArray) : Task<DoubleArray>(DoubleArray(B.size)) {

    val size = A.rows.size
    val P = DoubleArray(size + 1)
    val Q = DoubleArray(size + 1)

    override fun run() {
        echelonForm()
        inverseEchelonForm()
    }

    fun echelonForm() {
        P[1] = A[0][1] / -A[0][0]
        Q[1] = -B[0] / -A[0][0]
        for (i in 1..B.size - 1) {
            P[i + 1] = if (i != B.size - 1) A[i][i + 1] / (-A[i][i] - A[i][i - 1] * P[i]) else 0.0
            Q[i + 1] = (A[i][i - 1] * Q[i] - B[i]) / (-A[i][i] - A[i][i - 1] * P[i])
        }
    }

    fun inverseEchelonForm() {
        result[result.size - 1] = Q[result.size]
        for (i in result.size - 2 downTo 0) {
            result[i] = P[i + 1] * result[i + 1] + Q[i + 1]
        }
    }
}
