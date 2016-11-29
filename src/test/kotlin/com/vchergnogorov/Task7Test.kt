package com.vchergnogorov

import com.vchernogorov.matrix.DoubleMatrix
import com.vchernogorov.matrix.Task7
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class Task7Test {

    @Test
    fun test() {
        val matrix = DoubleMatrix(arrayOf(
                doubleArrayOf(3.0, 4.0, 0.0),
                doubleArrayOf(2.0, 2.0, 5.0),
                doubleArrayOf(0.0, 8.0, 1.0)))
        val vector = doubleArrayOf(7.0, 9.0, 9.0)
        val task = Task7(matrix, vector)
        task.run()
        val expected = doubleArrayOf(1.0, 1.0, 1.0)
        val actual = task.result.map { (if (it + 1e-9 > Math.round(it) && it < Math.round(it) ||
               it - 1e-9 < Math.round(it) && it > Math.round(it)) Math.round(it).toDouble() else it ) }.toDoubleArray()
        Assertions.assertArrayEquals(expected, actual)
    }
}