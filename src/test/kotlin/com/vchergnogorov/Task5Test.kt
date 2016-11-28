package com.vchergnogorov

import com.vchernogorov.matrix.DoubleMatrix
import com.vchernogorov.matrix.Task5
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task5Test {

    @Test
    fun test() {
        val matrix = DoubleMatrix(arrayOf(
                doubleArrayOf(10.0, 1.0, 0.0),
                doubleArrayOf(1.0, 20.0, 2.0),
                doubleArrayOf(0.0, 3.0, 30.0)))
        val vector = doubleArrayOf(11.0, 23.0, 33.0)
        val task = Task5(matrix, vector)
        task.run()
        val expected = doubleArrayOf(1.0, 1.0, 1.0)
        val actual = task.result
        Assertions.assertArrayEquals(expected, actual)
    }
}