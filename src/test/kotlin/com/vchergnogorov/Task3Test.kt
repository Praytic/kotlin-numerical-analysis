package com.vchergnogorov

import com.vchernogorov.Task3
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task3Test {

    @Test
    fun simpleTest() {
        val task3 = Task3()
        task3.task2.result = mutableMapOf(1.5 to 2.25, 2.5 to 6.25)
        task3.run()
        val actual = task3.result.values.toTypedArray()
        val expected = arrayOf(2.25, 6.25)
        Assertions.assertArrayEquals(expected, actual)
    }
}