package com.vchergnogorov

import com.vchernogorov.differential_equations.Task9
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task9Test {

    @Test
    fun test() {
        val task = Task9(1.0, 10.0, 10, 6)
        task.run()

        val expectedXs = DoubleArray(10, { (it + 1).toDouble() })
        val actualXs = task.xs
        Assertions.assertArrayEquals(expectedXs, actualXs)

        val expectedExpected = doubleArrayOf(8.0, 54.0, 174.0, 404.0, 780.0, 1338.0, 2114.0, 3144.0, 4464.0, 6110.00)
        val actualExpected = task.expected
        Assertions.assertArrayEquals(expectedExpected, actualExpected)

        val expectedResult = doubleArrayOf(8.00, 55.00, 173.00, 400.25, 773.3125, 1328.328125, 2101.33203125,
                3128.3330078125, 4445.333251953125, 6088.333312988281)
        val actualResult = task.result
        Assertions.assertArrayEquals(expectedResult, actualResult)

        val expectedDiff = DoubleArray(10, { Math.abs(expectedExpected[it] - expectedResult[it]) })
        val actualDiff = task.diff
        Assertions.assertArrayEquals(expectedDiff, actualDiff)
    }
}