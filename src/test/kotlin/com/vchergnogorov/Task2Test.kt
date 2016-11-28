package com.vchergnogorov

import com.vchernogorov.interpolation.Task2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task2Test {

    @Test
    fun simpleTest() {
        val task2 = Task2()
        task2.task1.result = mutableMapOf(1.0 to 1.0, 2.0 to 4.0, 3.0 to 9.0)
        task2.run()
        val actual = task2.result.values.toTypedArray()
        val expected = arrayOf(2.5, 6.5)
        Assertions.assertArrayEquals(expected, actual)
    }
}