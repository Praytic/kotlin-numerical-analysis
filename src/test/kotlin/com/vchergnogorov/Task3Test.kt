package com.vchergnogorov

import com.vchernogorov.Task3
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task3Test {

    @Test
    fun simpleTest() {
        val task2Result = mutableMapOf(Pair(1.5, 2.25), Pair(2.5, 6.25))
        val task3 = Task3(task2Result)
        task3.run()
        val actual = arrayOf(task3.result.values)
        val expected = arrayOf(2.25, 6.25)
        Assertions.assertArrayEquals(expected, actual)
    }
}