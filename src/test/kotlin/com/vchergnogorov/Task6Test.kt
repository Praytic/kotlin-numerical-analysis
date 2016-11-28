package com.vchergnogorov

import com.vchernogorov.matrix.DoubleMatrix
import com.vchernogorov.matrix.Task6
import org.junit.jupiter.api.Test

class Task6Test {

    @Test
    fun test() {
        val matrix = DoubleMatrix(arrayOf(
                doubleArrayOf(3.0, 4.0, 0.0),
                doubleArrayOf(2.0, 2.0, 5.0),
                doubleArrayOf(0.0, 8.0, 1.0)))
        val vector = doubleArrayOf(7.0, 9.0, 9.0)
        val task = Task6(matrix, vector)
        task.run()
    }
}