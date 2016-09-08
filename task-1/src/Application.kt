/**
 * Created by vchernogorov on 06/09/16.
 */
fun main(args: Array<String>) {
    Application().start()
}

class Application {
    internal var a = 1
    internal var b = 10
    internal var e = 10e-6
    internal var V = 6.0

    fun start() {
        for (i in a..b) {
            var result = 1.0
            var count = 1
            while (Math.abs(f(result, count * 2, i)) > e) {
                result += f(result, count * 2, i)
                count++
            }
            println("$result: $count")
        }
    }

    fun f(previous: Double, count: Int, x: Int): Double {
        var ans = previous
        ans *= V * x.toDouble() * x.toDouble() * V
        ans *= -1.0
        ans /= ((count - 1) * count).toDouble()
        return ans
    }
}
