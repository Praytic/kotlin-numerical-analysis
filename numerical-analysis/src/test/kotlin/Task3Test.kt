import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task3Test {

    @Test
    fun simpleTest() {
        val task1Result = mutableMapOf(Pair(1.0, 1.0), Pair(2.0, 4.0), Pair(3.0, 9.0))
        val task2Result = mutableMapOf(Pair(1.5, 1.5), Pair(2.5, 2.5))
        val task3 = Task3(task1Result, task2Result)
        task3.run()
        val result = arrayOf(1.0, 2.25, 4.0, 6.25, 9)
        Assertions.assertArrayEquals(result, arrayOf(task3.result.values))
    }
}