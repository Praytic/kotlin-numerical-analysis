class Task3 (val task1: MutableMap<Double, Double> = mutableMapOf(),
             val task2: MutableMap<Double, Double> = mutableMapOf(),
             val result: MutableMap<Double, Double> = mutableMapOf()) : Runnable {

    override fun run() {
        for ((x, y) in task2) {
            result.put(x, f(x))
        }
    }

    fun f(x: Double): Double {
        var result = 0.0
        for ((xk, yk) in task1) {
            var yk2 = yk
            for ((xi, yi) in task1) {
                if (xk != xi) {
                    yk2 *= (x - xi) / (xk - xi)
                }
            }
            result += yk2
        }
        return result
    }
}
